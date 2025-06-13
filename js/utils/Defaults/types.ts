import type { Options, Styles, Type } from "../../DatePicker/types";

export type Defaults = {
	/* Gets default date value */
	defaultValue: (type: Type, isMultiple: boolean) => Date[];

	/* Gets default options */
	defaultOptions: (
		type: Type,
		isMultiple: boolean,
		isInline: boolean
	) => Options;

	/* Gets default styles */
	defaultStyles: () => Styles;
};
