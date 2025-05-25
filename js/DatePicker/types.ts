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
