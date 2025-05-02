import React, { useMemo } from "react";
import type { DatePickerProps } from "../types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";

function DatePicker({
	isOpen,
	value: valueProp,
	onChange,
	onConfirm,
	onCancel,
}: DatePickerProps) {
	const value = useMemo(() => {
		// The selected date is expected to be at the start of the day
		// Ref: https://developer.android.com/reference/kotlin/androidx/compose/material3/DatePickerState#selectedDateMillis()
		return new Date(valueProp).setUTCHours(0, 0, 0, 0);
	}, [valueProp]);

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
