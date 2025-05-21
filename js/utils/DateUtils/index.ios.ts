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
	nativeValue * 1000;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch / 1000;

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

export const defaultDate: DefaultDate = (_type) => new Date();

export const defaultSize: DefaultSize = (type, isInline, options) => {
	if (!isInline) {
		return { width: 0, height: 0 };
	}

	if (type === "date") {
		switch (options.mode) {
			case "graphical":
				return { width: 320, height: 326 };
			case "wheel":
				return { width: 320, height: 216 };
			case "compact":
				return { width: 112, height: 37 };
			default:
		}
	}

	if (type === "time") {
		switch (options.mode) {
			case "graphical":
				return { width: 320, height: 54 };
			case "wheel":
				return { width: 320, height: 216 };
			case "compact":
				return { width: 87, height: 38 };
			default:
		}
	}

	return { width: undefined, height: undefined };
};

export const defaultOptions: DefaultOptions = (type) => ({
	mode: type === "date" ? "graphical" : "wheel",
});
