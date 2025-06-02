import type { NativeValues } from "./types";

const nativeValues: NativeValues = {
	nativeEpochToMilliseconds(nativeEpoch) {
		return nativeEpoch;
	},

	nativeEpochFromMilliseconds(milliseconds) {
		return milliseconds;
	},

	nativeStepFromSeconds(seconds, type) {
		switch (type) {
			case "date":
				return seconds / 86400;
			case "time":
				return seconds;
			case "datetime":
				return seconds;
			case "yearmonth":
				return seconds / 2628000;
		}
	},
};

export default nativeValues;
