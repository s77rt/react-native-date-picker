import type { Ref } from "react";
import type { ViewProps, ColorValue } from "react-native";

export type DatePickerHandle = {
	showPicker: () => void;
};

export type InternalChangeEvent = {
	value: number[];
};

export type InternalRange = {
	lowerBound?: number;
	upperBound?: number;
};

export type Type = "date" | "time" | "datetime" | "yearmonth";

export type Options = {
	locale?: string;
	confirmText?: string;
	cancelText?: string;
	mode?: "compact" | "graphical" | "wheel";
	title?: string;
	headline?: string;
	showModeToggle?: boolean;
	is24Hour?: boolean;
};

export type Styles = {
	accentColor?: ColorValue;
	containerColor?: ColorValue;
	titleContentColor?: ColorValue;
	headlineContentColor?: ColorValue;
	weekdayContentColor?: ColorValue;
	subheadContentColor?: ColorValue;
	navigationContentColor?: ColorValue;
	yearContentColor?: ColorValue;
	disabledYearContentColor?: ColorValue;
	currentYearContentColor?: ColorValue;
	selectedYearContentColor?: ColorValue;
	disabledSelectedYearContentColor?: ColorValue;
	selectedYearContainerColor?: ColorValue;
	disabledSelectedYearContainerColor?: ColorValue;
	dayContentColor?: ColorValue;
	disabledDayContentColor?: ColorValue;
	selectedDayContentColor?: ColorValue;
	disabledSelectedDayContentColor?: ColorValue;
	selectedDayContainerColor?: ColorValue;
	disabledSelectedDayContainerColor?: ColorValue;
	todayContentColor?: ColorValue;
	todayDateBorderColor?: ColorValue;
	dayInSelectionRangeContainerColor?: ColorValue;
	dayInSelectionRangeContentColor?: ColorValue;
	dividerColor?: ColorValue;
	clockDialColor?: ColorValue;
	selectorColor?: ColorValue;
	periodSelectorBorderColor?: ColorValue;
	clockDialSelectedContentColor?: ColorValue;
	clockDialUnselectedContentColor?: ColorValue;
	periodSelectorSelectedContainerColor?: ColorValue;
	periodSelectorUnselectedContainerColor?: ColorValue;
	periodSelectorSelectedContentColor?: ColorValue;
	periodSelectorUnselectedContentColor?: ColorValue;
	timeSelectorSelectedContainerColor?: ColorValue;
	timeSelectorUnselectedContainerColor?: ColorValue;
	timeSelectorSelectedContentColor?: ColorValue;
	timeSelectorUnselectedContentColor?: ColorValue;
};

type Value<TMultiple> = TMultiple extends true ? Date[] : Date | null;

export type DatePickerProps<TMultiple = boolean> = TMultiple extends boolean
	? ViewProps & {
			ref?: Ref<DatePickerHandle>;
			type?: Type;
			value?: Value<TMultiple>;
			onChange?: (value: Value<TMultiple>) => void;
			min?: Date;
			max?: Date;
			step?: number;
			multiple?: TMultiple;
			inline?: boolean;
			options?: Options;
			styles?: Styles;
	  }
	: never;
