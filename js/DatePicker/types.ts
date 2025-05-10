import type { Ref } from "react";

export type DatePickerHandle = {
	showPicker: () => void;
};

export type DatePickerProps = {
	ref?: Ref<DatePickerHandle>;
	value?: Date;
	onChange?: (value: Date) => void;
};
