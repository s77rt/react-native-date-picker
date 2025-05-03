import React, { useMemo, useCallback } from "react";
import type { NativeSyntheticEvent } from "react-native";
import type { DatePickerProps } from "../types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
import type { ChangeEvent } from "../RTNDatePickerNativeComponent";
import {
	nativeValueFromMsEpoch,
	nativeValueToMsEpoch,
} from "../utils/DateUtils";

function DatePicker({
	isOpen = false,
	value: valueProp,
	onChange: onChangeProp,
	onConfirm: onConfirmProp,
	onCancel: onCancelProp,
}: DatePickerProps) {
	const value = useMemo(() => {
		// On Android the selected date is expected to be at the start of the day in UTC
		// https://developer.android.com/reference/kotlin/androidx/compose/material3/DatePickerState#selectedDateMillis()
		//
		// PS: This is not required on iOS but kept for consistency
		const date = valueProp ?? new Date();
		const dateUTC = new Date(
			date.getTime() - date.getTimezoneOffset() * 60000
		);

		return nativeValueFromMsEpoch(dateUTC.setUTCHours(0, 0, 0, 0));
	}, [valueProp]);

	const onChange = useCallback(
		(event: NativeSyntheticEvent<ChangeEvent>) => {
			onChangeProp?.(
				new Date(nativeValueToMsEpoch(event.nativeEvent.value))
			);
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
