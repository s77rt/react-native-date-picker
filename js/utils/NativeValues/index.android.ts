import type { NativeValues } from "./types";

const nativeValues: NativeValues = {
	nativeEpochToMilliseconds(nativeEpoch) {
		return nativeEpoch;
	},

	nativeEpochFromMilliseconds(milliseconds) {
		return milliseconds;
	},

	nativeStepFromSeconds(_seconds, _type) {
		return NaN; // Not supported
	},
};

export default nativeValues;
