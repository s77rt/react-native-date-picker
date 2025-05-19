import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDateValue,
	DateToISO8601Date,
	DefaultSize,
	DateToHHmm,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch;

export const defaultDateValue: DefaultDateValue = (type: string) => {
	// TimePickerState`s hour and minute are non-nullable
	// https://developer.android.com/reference/kotlin/androidx/compose/material3/TimePickerState#summary
	if (type === "time") {
		return new Date();
	}

	return null;
};

export const dateToISO8601Date: DateToISO8601Date = (date: Date) =>
	[
		date.getFullYear(),
		("0" + (date.getMonth() + 1)).slice(-2),
		("0" + date.getDate()).slice(-2),
	].join("-");
export const dateToHHmm: DateToHHmm = (date: Date) =>
	[
		("0" + date.getHours()).slice(-2),
		("0" + date.getMinutes()).slice(-2),
	].join(":");

export const defaultSize: DefaultSize = (type: string, isInline: boolean) => {
	if (type === "date" && isInline) {
		return { width: 360, height: 392 };
	}

	if (type === "time" && isInline) {
		return { width: 280, height: 372 };
	}

	return { width: 0, height: 0 };
};
