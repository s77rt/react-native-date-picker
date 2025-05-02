export type DatePickerProps = {
	isOpen: boolean;
	value: Date;
	onChange: () => void;
	onConfirm: () => void;
	onCancel: () => void;
};
