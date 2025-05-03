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
		// The selected date is expected to be at the start of the day in UTC
		// Ref: https://developer.android.com/reference/kotlin/androidx/compose/material3/DatePickerState#selectedDateMillis()
		const date = new Date(valueProp ?? Date.now());
		const dateUTC = new Date(
			date.getTime() - date.getTimezoneOffset() * 60000
		);

		return dateUTC.setUTCHours(0, 0, 0, 0);
	}, [valueProp]);

	const onChange = useCallback(
		(event: NativeSyntheticEvent<ChangeEvent>) => {
			onChangeProp?.(new Date(event.nativeEvent.value));
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
