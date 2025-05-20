import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDate,
	DateToISO8601Date,
	DefaultSize,
	DateToHHmm,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue * 1000;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch / 1000;

export const defaultDate: DefaultDate = (_type: string) => new Date();

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
		return { width: 320, height: 326 };
	}

	if (type === "time" && isInline) {
		return { width: 320, height: 216 };
	}

	return { width: 0, height: 0 };
};
