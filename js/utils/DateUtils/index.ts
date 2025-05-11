import type {
	NativeValueToMsEpoch,
	NativeValueFromMsEpoch,
	DefaultDateValue,
} from "./types";

export const nativeValueToMsEpoch: NativeValueToMsEpoch = (nativeValue) =>
	nativeValue;
export const nativeValueFromMsEpoch: NativeValueFromMsEpoch = (msEpoch) =>
	msEpoch;
export const defaultDateValue: DefaultDateValue = () => null;
