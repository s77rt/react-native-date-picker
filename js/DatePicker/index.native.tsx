import React, {
	useCallback,
	useMemo,
	useState,
	useImperativeHandle,
} from "react";
import { StyleSheet } from "react-native";
import type { NativeSyntheticEvent } from "react-native";
import type { DatePickerProps } from "./types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
import type { ChangeEvent, Range } from "../RTNDatePickerNativeComponent";
import {
	defaultDateValue,
	defaultSize,
	nativeValueFromMsEpoch,
	nativeValueToMsEpoch,
} from "../utils/DateUtils";

function DatePicker({
	ref,
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
	isInline = false,
	style: styleProp,
	...rest
}: DatePickerProps) {
	const style = useMemo(
		() =>
			StyleSheet.compose(
				{
					...defaultSize(isInline),
				} as const,
				styleProp
			),
		[isInline, styleProp]
	);

	const [isOpen, setIsOpen] = useState(false);

	const range = useMemo<Range>(
		() => ({
			lowerBound:
				minProp === undefined
					? undefined
					: nativeValueFromMsEpoch(minProp.getTime()),
			upperBound:
				maxProp === undefined
					? undefined
					: nativeValueFromMsEpoch(maxProp.getTime()),
		}),
		[minProp, maxProp]
	);

	const initialValue = useMemo(() => {
		const date = valueProp ?? defaultDateValue();
		if (date === null) {
			return null;
		}
		const dateValue = nativeValueFromMsEpoch(date.getTime());
		if (range.lowerBound !== undefined && dateValue < range.lowerBound) {
			return range.lowerBound;
		}
		if (range.upperBound !== undefined && dateValue > range.upperBound) {
			return range.upperBound;
		}
		return dateValue;
	}, [valueProp, range]);

	const [value, setValue] = useState(initialValue);
	const onChange = useCallback((event: NativeSyntheticEvent<ChangeEvent>) => {
		setValue(event.nativeEvent.value);
	}, []);

	const onConfirm = useCallback(() => {
		const date =
			value === null ? null : new Date(nativeValueToMsEpoch(value));
		onChangeProp?.(date);
		setIsOpen(false);
	}, [value, onChangeProp]);

	const onCancel = useCallback(() => {
		setValue(initialValue);
		setIsOpen(false);
	}, [initialValue]);

	useImperativeHandle(
		ref,
		() => ({
			showPicker() {
				setValue(initialValue);
				setIsOpen(true);
			},
		}),
		[initialValue]
	);

	return (
		<RTNDatePickerNativeComponent
			style={style}
			isOpen={isOpen}
			isInline={isInline}
			value={value}
			onChange={onChange}
			onConfirm={onConfirm}
			onCancel={onCancel}
			range={range}
			{...rest}
		/>
	);
}

export default React.memo(DatePicker);
