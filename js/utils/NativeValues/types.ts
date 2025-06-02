import type { Type } from "../../DatePicker/types";

export type NativeValues = {
	/* Converts a native epoch (could be in seconds or in milliseconds) to milliseconds */
	nativeEpochToMilliseconds: (nativeEpoch: number) => number;

	/* Converts a milliseconds epoch to native epoch */
	nativeEpochFromMilliseconds: (milliseconds: number) => number;

	/* Converts seconds step to type-dependent step */
	nativeStepFromSeconds: (seconds: number, type: Type) => number;
};
