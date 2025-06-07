import type { Defaults } from "./types";

const defaults: Defaults = {
	defaultValue(type, _isMultiple) {
		// TimePickerState`s hour and minute are non-nullable
		// https://developer.android.com/reference/kotlin/androidx/compose/material3/TimePickerState#summary
		if (type === "time") {
			return [new Date()];
		}

		return [];
	},

	defaultOptions(_type, isInline) {
		return {
			title: isInline ? "" : undefined,
			headline: isInline ? "" : undefined,
			showModeToggle: !isInline,
		};
	},

	defaultStyles() {
		return {};
	},
};

export default defaults;
