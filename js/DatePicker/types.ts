import type { Ref } from "react";
import type { ViewProps } from "react-native";

export type DatePickerHandle = {
	showPicker: () => void;
};

export type DatePickerProps = ViewProps & {
	ref?: Ref<DatePickerHandle>;
	type?: "date" | "time";
	value?: Date | null;
	onChange?: (value: Date | null) => void;
	min?: Date;
	max?: Date;
	inline?: boolean;
};
