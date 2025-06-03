import type { Options, Styles, Type } from "../../DatePicker/types";

export type Defaults = {
	/* Gets default date value */
	defaultValue: (type: Type) => Date | null;

	/* Gets default options */
	defaultOptions: (type: Type, isInline: boolean) => Options;

	/* Gets default styles */
	defaultStyles: () => Styles;
};
