import type { NativeValues } from "./types";

const nativeValues: NativeValues = {
	nativeEpochToMilliseconds(nativeEpoch) {
		return nativeEpoch;
	},

	nativeEpochFromMilliseconds(milliseconds) {
		return milliseconds;
	},

	nativeStepFromSeconds(_seconds, _type) {
		return -1; // Not supported
	},
};

export default nativeValues;
