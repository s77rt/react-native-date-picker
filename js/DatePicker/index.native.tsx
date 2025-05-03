import React, { useMemo, useCallback, useRef } from "react";
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

	const displayedValue = useRef(value);
	const selectedValue = useRef(value);

	const onChange = useCallback(
		(event: NativeSyntheticEvent<ChangeEvent>) => {
			displayedValue.current = event.nativeEvent.value;
			onChangeProp?.(
				new Date(nativeValueToMsEpoch(displayedValue.current))
			);
		},
		[onChangeProp]
	);

	const onConfirm = useCallback(() => {
		selectedValue.current = displayedValue.current;
		onConfirmProp?.(new Date(nativeValueToMsEpoch(selectedValue.current)));
	}, [onConfirmProp]);

	const onCancel = useCallback(() => {
		onCancelProp?.(new Date(nativeValueToMsEpoch(selectedValue.current)));
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
