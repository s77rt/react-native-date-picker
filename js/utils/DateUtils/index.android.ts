import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDate,
	DateToISO8601Date,
	DefaultSize,
	DateToHHmm,
	DefaultOptions,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch;

export const dateToISO8601Date: DateToISO8601Date = (date) =>
	[
		date.getFullYear(),
		("0" + (date.getMonth() + 1)).slice(-2),
		("0" + date.getDate()).slice(-2),
	].join("-");
export const dateToHHmm: DateToHHmm = (date) =>
	[
		("0" + date.getHours()).slice(-2),
		("0" + date.getMinutes()).slice(-2),
	].join(":");

export const defaultDate: DefaultDate = (type) => {
	// TimePickerState`s hour and minute are non-nullable
	// https://developer.android.com/reference/kotlin/androidx/compose/material3/TimePickerState#summary
	if (type === "time") {
		return new Date();
	}

	return null;
};

export const defaultSize: DefaultSize = (type, isInline, _options) => {
	if (!isInline) {
		return { width: 0, height: 0 };
	}

	if (type === "date") {
		return { width: 360, height: 392 };
	}

	if (type === "time") {
		return { width: 280, height: 372 };
	}

	return { width: undefined, height: undefined };
};

export const defaultOptions: DefaultOptions = (_type) => ({});
