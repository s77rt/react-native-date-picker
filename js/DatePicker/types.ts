import type { Ref } from "react";
import type { ViewProps, ColorValue } from "react-native";

export type DatePickerHandle = {
	showPicker: () => void;
};

export type InternalChangeEvent = {
	value: number | null;
};

export type InternalRange = {
	lowerBound?: number;
	upperBound?: number;
};

export type Options = {
	confirmText?: string;
	cancelText?: string;
	mode?: "compact" | "graphical" | "wheel";
};

export type Styles = {
	accentColor?: ColorValue;
};

export type DatePickerProps = ViewProps & {
	ref?: Ref<DatePickerHandle>;
	type?: "date" | "time";
	value?: Date | null;
	onChange?: (value: Date | null) => void;
	min?: Date;
	max?: Date;
	inline?: boolean;
	options?: Options;
	styles?: Styles;
};
