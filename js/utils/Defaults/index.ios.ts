import type { Defaults } from "./types";

const defaults: Defaults = {
	defaultValue(_type, isMultiple) {
		if (isMultiple) {
			return [];
		}

		// UIDatePicker always have a set value
		return [new Date()];
	},

	defaultOptions(type, _isMultiple, _isInline) {
		return {
			mode:
				type === "time" || type === "yearmonth" ? "wheel" : "graphical",
		};
	},

	defaultStyles() {
		return {};
	},
};

export default defaults;
