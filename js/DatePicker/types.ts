import type { Ref } from "react";

export type DatePickerHandle = {
	showPicker: () => void;
};

export type DatePickerProps = {
	ref?: Ref<DatePickerHandle>;
	value?: Date | null;
	onChange?: (value: Date | null) => void;
	min?: Date;
	max?: Date;
};
