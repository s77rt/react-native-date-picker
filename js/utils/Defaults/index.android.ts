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

	defaultOptions(_type, isMultiple, isInline) {
		return {
			title: isInline ? "" : undefined,
			headline: isInline ? "" : undefined,

			// Ideally the mode toggle button should be shown in multiple selection modal too
			// but currently it's causing the headline text to wrap
			showModeToggle: !isInline && !isMultiple,
		};
	},

	defaultStyles() {
		return {};
	},
};

export default defaults;
