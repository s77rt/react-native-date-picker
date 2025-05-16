import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
	useEffect,
} from "react";
import type { ChangeEvent } from "react";
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
	inline: isInline = false,
}: DatePickerProps) {
	const inputRef = useRef<HTMLInputElement>(null);

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

	const style = useMemo(
		() =>
			({
				position: "absolute",
				opacity: 0,
				zIndex: -9999,
				pointerEvents: "none",
				...defaultSize(isInline),
			} as const),
		[isInline]
	);

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
		if (!isInline) {
			return;
		}
		console.warn(
			"@s77rt/react-native-date-picker: isInline is not supported on web."
		);
	}, [isInline]);

	return (
		<input
			ref={inputRef}
			type="date"
			value={value}
			onChange={onChange}
			min={min}
			max={max}
			style={style}
			tabIndex={-1}
		/>
	);
}

export default React.memo(DatePicker);
