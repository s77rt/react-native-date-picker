import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
} from "react";
import type { ChangeEvent } from "react";
import type { DatePickerProps } from "./types";
import {
	dateToHHmm,
	dateToISO8601Date,
	dateToISO8601DateTime,
	dateToYYMM,
	defaultDate,
} from "../utils/DateUtils";

function DatePicker({
	ref,
	type = "date",
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
}: DatePickerProps) {
	const inputRef = useRef<HTMLInputElement>(null);

	const inputType = useMemo(() => {
		switch (type) {
			case "date":
				return "date";
			case "time":
				return "time";
			case "datetime":
				return "datetime-local";
			case "yearmonth":
				return "month";
		}
	}, [type]);

	const value = useMemo(() => {
		const date = valueProp ?? defaultDate(type);
		if (date === null) {
			return "";
		}

		switch (type) {
			case "date":
				return dateToISO8601Date(date);
			case "time":
				return dateToHHmm(date);
			case "datetime":
				return dateToISO8601DateTime(date);
			case "yearmonth":
				return dateToYYMM(date);
		}
	}, [type, valueProp]);

	const onChange = useCallback(
		(event: ChangeEvent<HTMLInputElement>) => {
			// datetime does not provide a valueAsDate (always null)
			const date =
				type === "datetime"
					? new Date(event.target.value)
					: event.target.valueAsDate;
			onChangeProp?.(date);
		},
		[type, onChangeProp]
	);

	const min = useMemo(() => {
		const date = minProp;
		if (date === undefined) {
			return undefined;
		}

		switch (type) {
			case "date":
				return dateToISO8601Date(date);
			case "time":
				return dateToHHmm(date);
			case "datetime":
				return dateToISO8601DateTime(date);
			case "yearmonth":
				return dateToYYMM(date);
		}
	}, [type, minProp]);

	const max = useMemo(() => {
		const date = maxProp;
		if (date === undefined) {
			return undefined;
		}

		switch (type) {
			case "date":
				return dateToISO8601Date(date);
			case "time":
				return dateToHHmm(date);
			case "datetime":
				return dateToISO8601DateTime(date);
			case "yearmonth":
				return dateToYYMM(date);
		}
	}, [type, maxProp]);

	const style = useMemo(
		() =>
			({
				position: "absolute",
				opacity: 0,
				zIndex: -9999,
				pointerEvents: "none",
				width: 0,
				height: 0,
			} as const),
		[]
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

	return (
		<input
			ref={inputRef}
			type={inputType}
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
