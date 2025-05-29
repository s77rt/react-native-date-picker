import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDate,
	DateToISO8601Date,
	DateToHHmm,
	DefaultOptions,
	DefaultStyles,
	DateToISO8601DateTime,
	DateToYYMM,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue * 1000;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch / 1000;

export const dateToISO8601DateTime: DateToISO8601DateTime = (date) => {
	const year = date.getFullYear();
	const month = ("0" + (date.getMonth() + 1)).slice(-2);
	const day = ("0" + date.getDate()).slice(-2);
	const hours = ("0" + date.getHours()).slice(-2);
	const minutes = ("0" + date.getMinutes()).slice(-2);

	return `${year}-${month}-${day}T${hours}:${minutes}`;
};
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
export const dateToYYMM: DateToYYMM = (date) =>
	[date.getFullYear(), ("0" + (date.getMonth() + 1)).slice(-2)].join("-");

export const defaultDate: DefaultDate = (_type) => new Date();

export const defaultOptions: DefaultOptions = (type, _isInline) => ({
	mode: type === "time" || type === "yearmonth" ? "wheel" : "graphical",
});

export const defaultStyles: DefaultStyles = () => ({});
