import React, {
	useCallback,
	useMemo,
	useState,
	useImperativeHandle,
} from "react";
import type { NativeSyntheticEvent } from "react-native";
import type {
	DatePickerProps,
	InternalChangeEvent,
	InternalRange,
} from "./types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
import {
	defaultDate,
	defaultOptions,
	defaultSize,
	nativeValueFromMsEpoch,
	nativeValueToMsEpoch,
} from "../utils/DateUtils";

function DatePicker({
	ref,
	type = "date",
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
	inline: isInline = false,
	options: optionsProp,
	style: styleProp,
	...rest
}: DatePickerProps) {
	const [isOpen, setIsOpen] = useState(false);

	const range = useMemo<InternalRange>(
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
		const date = valueProp ?? defaultDate(type);
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
	}, [type, valueProp, range]);

	const [prevInitialValue, setPrevInitialValue] = useState(initialValue);

	const [value, setValue] = useState(initialValue);

	if (initialValue !== prevInitialValue) {
		setPrevInitialValue(initialValue);
		setValue(initialValue);
	}

	const onChange = useCallback(
		(event: NativeSyntheticEvent<InternalChangeEvent>) => {
			setValue(event.nativeEvent.value);

			// In inline mode every change is considered a confirmed change
			// since there is no explicit confirm button
			if (isInline) {
				const date =
					event.nativeEvent.value === null
						? null
						: new Date(
								nativeValueToMsEpoch(event.nativeEvent.value)
						  );
				onChangeProp?.(date);
			}
		},
		[isInline, onChangeProp]
	);

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

	const options = useMemo(
		() => ({ ...defaultOptions(type), ...optionsProp }),
		[type, optionsProp]
	);

	const style = useMemo(
		() => [
			{
				...defaultSize(type, isInline),
			} as const,
			styleProp,
		],
		[type, isInline, styleProp]
	);

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
			type={type}
			isOpen={isOpen}
			isInline={isInline}
			value={value}
			onChange={onChange}
			onConfirm={onConfirm}
			onCancel={onCancel}
			range={range}
			options={options}
			style={style}
			{...rest}
		/>
	);
}

export default React.memo(DatePicker);
