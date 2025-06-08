import React, {
	useCallback,
	useMemo,
	useState,
	useImperativeHandle,
} from "react";
import { processColor } from "react-native";
import type { NativeSyntheticEvent } from "react-native";
import type {
	DatePickerProps,
	InternalChangeEvent,
	InternalRange,
} from "./types";
import RTNDatePickerNativeComponent from "../RTNDatePickerNativeComponent";
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
	inline: isInline = false,
	options: optionsProp,
	styles: stylesProp,
	...rest
}: DatePickerProps) {
	const [isOpen, setIsOpen] = useState(false);

	const range = useMemo<InternalRange>(
		() => ({
			lowerBound:
				minProp === undefined
					? undefined
					: NativeValues.nativeEpochFromMilliseconds(
							minProp.getTime()
					  ),
			upperBound:
				maxProp === undefined
					? undefined
					: NativeValues.nativeEpochFromMilliseconds(
							maxProp.getTime()
					  ),
		}),
		[minProp, maxProp]
	);

	const initialValue = useMemo(() => {
		const dates = isMultiple
			? valueProp && valueProp.length > 0
				? valueProp
				: Defaults.defaultValue(type, true)
			: valueProp
			? [valueProp]
			: Defaults.defaultValue(type, false);
		return dates.map((date) => {
			const dateValue = NativeValues.nativeEpochFromMilliseconds(
				date.getTime()
			);
			if (
				range.lowerBound !== undefined &&
				dateValue < range.lowerBound
			) {
				return range.lowerBound;
			}
			if (
				range.upperBound !== undefined &&
				dateValue > range.upperBound
			) {
				return range.upperBound;
			}
			return dateValue;
		});
	}, [type, isMultiple, valueProp, range]);

	const [prevInitialValue, setPrevInitialValue] = useState(initialValue);

	const [value, setValue] = useState(initialValue);

	if (initialValue !== prevInitialValue) {
		setPrevInitialValue(initialValue);
		setValue(initialValue);
	}

	const onChange = useCallback(
		(event: NativeSyntheticEvent<InternalChangeEvent>) => {
			setValue(event.nativeEvent.value);

			// In inline mode every change is considered a confirmed change
			// since there is no explicit confirm button
			if (isInline) {
				const dates = event.nativeEvent.value.map(
					(dateValue) =>
						new Date(
							NativeValues.nativeEpochToMilliseconds(dateValue)
						)
				);
				if (isMultiple) {
					onChangeProp?.(dates);
				} else {
					onChangeProp?.(dates[0] ?? null);
				}
			}
		},
		[isMultiple, isInline, onChangeProp]
	);

	const onConfirm = useCallback(() => {
		const dates = value.map(
			(dateValue) =>
				new Date(NativeValues.nativeEpochToMilliseconds(dateValue))
		);
		if (isMultiple) {
			onChangeProp?.(dates);
		} else {
			onChangeProp?.(dates[0] ?? null);
		}
		setIsOpen(false);
	}, [isMultiple, value, onChangeProp]);

	const onCancel = useCallback(() => {
		setValue(initialValue);
		setIsOpen(false);
	}, [initialValue]);

	const step = useMemo(
		() =>
			stepProp === undefined
				? undefined
				: NativeValues.nativeStepFromSeconds(stepProp, type),
		[type, stepProp]
	);

	const options = useMemo(
		() => ({ ...Defaults.defaultOptions(type, isInline), ...optionsProp }),
		[type, isInline, optionsProp]
	);

	const styles = useMemo(() => {
		const stylesToProcess = { ...Defaults.defaultStyles(), ...stylesProp };

		Object.keys(stylesToProcess).forEach((key) => {
			if (key.endsWith("Color")) {
				stylesToProcess[key] = processColor(stylesToProcess[key]);
				return;
			}
		});

		return stylesToProcess;
	}, [stylesProp]);

	useImperativeHandle(
		ref,
		() => ({
			showPicker() {
				setValue(initialValue);
				setIsOpen(true);
			},
		}),
		[initialValue]
	);

	return (
		<RTNDatePickerNativeComponent
			type={type}
			isOpen={isOpen}
			isInline={isInline}
			value={value}
			onChange={onChange}
			onConfirm={onConfirm}
			onCancel={onCancel}
			range={range}
			step={step}
			options={options}
			styles={styles}
			{...rest}
		/>
	);
}

export default React.memo(DatePicker);
