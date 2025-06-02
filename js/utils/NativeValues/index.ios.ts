import type { NativeValues } from "./types";

const nativeValues: NativeValues = {
	nativeEpochToMilliseconds(nativeEpoch) {
		return nativeEpoch * 1000;
	},

	nativeEpochFromMilliseconds(milliseconds) {
		return milliseconds / 1000;
	},

	nativeStepFromSeconds(seconds, _type) {
		// Step is used as minuteInterval
		return seconds / 60;
	},
};

export default nativeValues;
