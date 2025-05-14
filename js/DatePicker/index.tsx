import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
	useEffect,
} from "react";
import type { ChangeEvent } from "react";
import { StyleSheet } from "react-native";
import type { DatePickerProps } from "./types";
import {
	dateToISO8601Date,
	defaultDateValue,
	defaultSize,
} from "../utils/DateUtils";

function DatePicker({
	ref,
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
	inline = false,
	style: styleProp,
	...rest
}: DatePickerProps) {
	const inputRef = useRef<HTMLInputElement>(null);

	const style = useMemo(
		() =>
			StyleSheet.compose(
				{
					position: "absolute",
					opacity: 0,
					zIndex: -9999,
					pointerEvents: "none",
					...defaultSize(inline),
				} as const,
				styleProp
			),
		[inline, styleProp]
	);

	const value = useMemo(() => {
		const date = valueProp ?? defaultDateValue();
		if (date === null) {
			return "";
		}
		return dateToISO8601Date(date);
	}, [valueProp]);

	const onChange = useCallback(
		(event: ChangeEvent<HTMLInputElement>) => {
			onChangeProp?.(event.target.valueAsDate);
		},
		[onChangeProp]
	);

	const min = useMemo(() => {
		const date = minProp;
		if (date === undefined) {
			return undefined;
		}
		return dateToISO8601Date(date);
	}, [minProp]);

	const max = useMemo(() => {
		const date = maxProp;
		if (date === undefined) {
			return undefined;
		}
		return dateToISO8601Date(date);
	}, [maxProp]);

	useImperativeHandle(
		ref,
		() => ({
			showPicker() {
				inputRef.current?.focus();
				inputRef.current?.showPicker();
			},
		}),
		[]
	);

	useEffect(() => {
		if (!inline) {
			return;
		}
		console.warn(
			"@s77rt/react-native-date-picker: inline is not supported on web."
		);
	}, [inline]);

	return (
		<input
			ref={inputRef}
			style={style}
			type="date"
			value={value}
			onChange={onChange}
			min={min}
			max={max}
			tabIndex={-1}
			{...rest}
		/>
	);
}

export default React.memo(DatePicker);
