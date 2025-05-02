import React, { useMemo, useCallback } from "react";
import type { NativeSyntheticEvent } from "react-native";
import type { DatePickerProps } from "../types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
import type { ChangeEvent } from "../RTNDatePickerNativeComponent";

function DatePicker({
	isOpen = false,
	value: valueProp,
	onChange: onChangeProp,
	onConfirm: onConfirmProp,
	onCancel: onCancelProp,
}: DatePickerProps) {
	const value = useMemo(() => {
		// Set the date to the start of the day for consistency with Android
		return new Date(valueProp ?? Date.now()).setUTCHours(0, 0, 0, 0) / 1000;
	}, [valueProp]);

	const onChange = useCallback(
		(event: NativeSyntheticEvent<ChangeEvent>) => {
			onChangeProp?.(new Date(event.nativeEvent.value * 1000));
		},
		[onChangeProp]
	);

	const onConfirm = useCallback(() => {
		onConfirmProp?.();
	}, [onConfirmProp]);

	const onCancel = useCallback(() => {
		onCancelProp?.();
	}, [onCancelProp]);

	return (
		<RTNDatePickerNativeComponent
			isOpen={isOpen}
			value={value}
			onChange={onChange}
			onConfirm={onConfirm}
			onCancel={onCancel}
		/>
	);
}

export default React.memo(DatePicker);
