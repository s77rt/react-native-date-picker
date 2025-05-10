import React, {
	useCallback,
	useMemo,
	useState,
	useImperativeHandle,
} from "react";
import type { NativeSyntheticEvent } from "react-native";
import type { DatePickerProps } from "./types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
import type { ChangeEvent } from "../RTNDatePickerNativeComponent";
import {
	nativeValueFromMsEpoch,
	nativeValueToMsEpoch,
} from "../utils/DateUtils";

function DatePicker({ value, onChange, ref }: DatePickerProps) {
	const initialDisplayedValue = useMemo(() => {
		const date = value ?? new Date();
		return nativeValueFromMsEpoch(date.getTime());
	}, [value]);

	const [isOpen, setIsOpen] = useState(false);

	const [displayedValue, setDisplayedValue] = useState(initialDisplayedValue);
	const onDisplayedValueChange = useCallback(
		(event: NativeSyntheticEvent<ChangeEvent>) => {
			setDisplayedValue(event.nativeEvent.value);
		},
		[]
	);

	const onConfirm = useCallback(() => {
		onChange?.(new Date(nativeValueToMsEpoch(displayedValue)));
		setIsOpen(false);
	}, [displayedValue, onChange]);

	const onCancel = useCallback(() => {
		setDisplayedValue(initialDisplayedValue);
		setIsOpen(false);
	}, [initialDisplayedValue]);

	useImperativeHandle(
		ref,
		() => ({
			showPicker() {
				setDisplayedValue(initialDisplayedValue);
				setIsOpen(true);
			},
		}),
		[initialDisplayedValue]
	);

	return (
		<RTNDatePickerNativeComponent
			isOpen={isOpen}
			value={displayedValue}
			onChange={onDisplayedValueChange}
			onConfirm={onConfirm}
			onCancel={onCancel}
		/>
	);
}

export default React.memo(DatePicker);
