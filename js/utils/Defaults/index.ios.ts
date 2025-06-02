import type { Defaults } from "./types";

const defaults: Defaults = {
	defaultValue(_type) {
		return new Date();
	},

	defaultOptions(type, _isInline) {
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
