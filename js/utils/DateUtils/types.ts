import type { Options, Styles, Type } from "../../DatePicker/types";

export type NativeValueToMsEpoch = (nativeValue: number) => number;
export type NativeValueFromMsEpoch = (msEpoch: number) => number;

export type DateToISO8601DateTime = (date: Date) => string;
export type DateToISO8601Date = (date: Date) => string;
export type DateToHHmm = (date: Date) => string;
export type DateToYYMM = (date: Date) => string;

export type DefaultDate = (type: Type) => Date | null;

export type DefaultOptions = (type: Type, isInline: boolean) => Options;

export type DefaultStyles = () => Styles;
