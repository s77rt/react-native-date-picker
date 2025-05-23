import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDate,
	DateToISO8601Date,
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

export const defaultOptions: DefaultOptions = (_type) => ({});
