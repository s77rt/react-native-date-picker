import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDateValue,
	DateToISO8601Date,
	DefaultSize,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue * 1000;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch / 1000;
export const defaultDateValue: DefaultDateValue = () => new Date();
export const dateToISO8601Date: DateToISO8601Date = (date: Date) =>
	[
		date.getFullYear(),
		("0" + (date.getMonth() + 1)).slice(-2),
		("0" + date.getDate()).slice(-2),
	].join("-");
export const defaultSize: DefaultSize = (inline: boolean) =>
	inline ? { width: 320, height: 320 } : { width: 0, height: 0 };
