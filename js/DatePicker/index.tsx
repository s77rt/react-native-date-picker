import React, {
	useRef,
	useMemo,
	useCallback,
	useImperativeHandle,
} from "react";
import type { ChangeEvent } from "react";
import type { DatePickerProps } from "./types";

function DatePicker({
	value: valueProp,
	onChange: onChangeProp,
	ref,
}: DatePickerProps) {
	const inputRef = useRef<HTMLInputElement>(null);

	const value = useMemo(() => {
		const date = valueProp ?? null;
		if (!date) {
			return "";
		}
		return [
			date.getFullYear(),
			("0" + (date.getMonth() + 1)).slice(-2),
			("0" + date.getDate()).slice(-2),
		].join("-");
	}, [valueProp]);

	const onChange = useCallback(
		(event: ChangeEvent<HTMLInputElement>) => {
			onChangeProp?.(event.target.valueAsDate);
		},
		[onChangeProp]
	);

	useImperativeHandle(
		ref,
		() => ({
			showPicker() {
				inputRef.current?.showPicker();
			},
		}),
		[]
	);

	return (
		<input ref={inputRef} type="date" value={value} onChange={onChange} />
	);
}

export default React.memo(DatePicker);
