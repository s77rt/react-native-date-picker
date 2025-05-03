export type DatePickerProps = {
	isOpen?: boolean;
	value?: Date;
	onChange?: (value: Date) => void;
	onConfirm?: (value: Date) => void;
	onCancel?: (value: Date) => void;
};
