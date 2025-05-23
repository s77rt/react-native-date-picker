import type { Options } from "../../DatePicker/types";

export type NativeValueToMsEpoch = (nativeValue: number) => number;
export type NativeValueFromMsEpoch = (msEpoch: number) => number;

export type DateToISO8601Date = (date: Date) => string;
export type DateToHHmm = (date: Date) => string;

export type DefaultDate = (type: "date" | "time") => Date | null;

export type DefaultOptions = (type: "date" | "time") => Options;
