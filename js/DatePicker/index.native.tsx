import React, { useMemo, useCallback } from "react";
import type { NativeSyntheticEvent } from "react-native";
import type { DatePickerProps } from "./types";
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
		const date = valueProp ?? new Date();
		return nativeValueFromMsEpoch(date.getTime());
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
