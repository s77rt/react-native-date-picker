export type DatePickerProps = {
	isOpen?: boolean;
	value?: Date;
	onChange?: (value: Date) => void;
	onConfirm?: () => void;
	onCancel?: () => void;
};
