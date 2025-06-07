import type { Defaults } from "./types";

const defaults: Defaults = {
	defaultValue(_type, _isMultiple) {
		return [];
	},

	defaultOptions(_type, _isInline) {
		return {};
	},

	defaultStyles() {
		return {};
	},
};

export default defaults;
