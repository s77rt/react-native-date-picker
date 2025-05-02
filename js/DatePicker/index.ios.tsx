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
		// Set the date to the start of the day for consistency with Android
		return new Date(valueProp).setUTCHours(0, 0, 0, 0) / 1000;
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
