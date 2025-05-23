import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
} from "react";
import type { ChangeEvent } from "react";
import type { DatePickerProps } from "./types";
import { dateToHHmm, dateToISO8601Date, defaultDate } from "../utils/DateUtils";

function DatePicker({
	ref,
	type = "date",
	value: valueProp,
	onChange: onChangeProp,
	min: minProp,
	max: maxProp,
}: DatePickerProps) {
	const inputRef = useRef<HTMLInputElement>(null);

	const value = useMemo(() => {
		const date = valueProp ?? defaultDate(type);
		if (date === null) {
			return "";
		}
		return type == "date" ? dateToISO8601Date(date) : dateToHHmm(date);
	}, [type, valueProp]);

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
		return type == "date" ? dateToISO8601Date(date) : dateToHHmm(date);
	}, [type, minProp]);

	const max = useMemo(() => {
		const date = maxProp;
		if (date === undefined) {
			return undefined;
		}
		return type == "date" ? dateToISO8601Date(date) : dateToHHmm(date);
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
			type={type}
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
