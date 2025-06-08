import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
} from "react";
import type { ChangeEvent } from "react";
import type { DatePickerProps } from "./types";
import DateFormat from "../utils/DateFormat";
import Defaults from "../utils/Defaults";
import NativeValues from "../utils/NativeValues";

function DatePicker({
	ref,
	type = "date",
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
	step: stepProp,
	multiple: isMultiple,
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
		const dates = isMultiple
			? valueProp && valueProp.length > 0
				? valueProp
				: Defaults.defaultValue(type, true)
			: valueProp
			? [valueProp]
			: Defaults.defaultValue(type, false);
		const date = dates[0] ?? null;
		if (date === null) {
			return "";
		}
		switch (type) {
			case "date":
				return DateFormat.dateToISO8601Date(date);
			case "time":
				return DateFormat.dateToHHmm(date);
			case "datetime":
				return DateFormat.dateToISO8601DateTime(date);
			case "yearmonth":
				return DateFormat.dateToYYYYMM(date);
		}
	}, [type, isMultiple, valueProp]);

	const onChange = useCallback(
		(event: ChangeEvent<HTMLInputElement>) => {
			// datetime does not provide a valueAsDate (always null)
			const date =
				type === "datetime"
					? event.target.value
						? new Date(event.target.value)
						: null
					: event.target.valueAsDate;
			if (isMultiple) {
				onChangeProp?.(date ? [date] : []);
			} else {
				onChangeProp?.(date);
			}
		},
		[type, isMultiple, onChangeProp]
	);

	const min = useMemo(() => {
		const date = minProp;
		if (date === undefined) {
			return undefined;
		}
		switch (type) {
			case "date":
				return DateFormat.dateToISO8601Date(date);
			case "time":
				return DateFormat.dateToHHmm(date);
			case "datetime":
				return DateFormat.dateToISO8601DateTime(date);
			case "yearmonth":
				return DateFormat.dateToYYYYMM(date);
		}
	}, [type, minProp]);

	const max = useMemo(() => {
		const date = maxProp;
		if (date === undefined) {
			return undefined;
		}
		switch (type) {
			case "date":
				return DateFormat.dateToISO8601Date(date);
			case "time":
				return DateFormat.dateToHHmm(date);
			case "datetime":
				return DateFormat.dateToISO8601DateTime(date);
			case "yearmonth":
				return DateFormat.dateToYYYYMM(date);
		}
	}, [type, maxProp]);

	const step = useMemo(
		() =>
			stepProp === undefined
				? undefined
				: NativeValues.nativeStepFromSeconds(stepProp, type),
		[type, stepProp]
	);

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
			step={step}
			style={style}
			tabIndex={-1}
		/>
	);
}

export default React.memo(DatePicker);
