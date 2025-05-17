export type NativeValueToMsEpoch = (nativeValue: number) => number;
export type NativeValueFromMsEpoch = (msEpoch: number) => number;

export type DefaultDateValue = () => Date | null;
export type DateToISO8601Date = (date: Date) => string;

export type DefaultSize = (
	type: string,
	isInline: boolean
) => {
	width: number;
	height: number;
};
