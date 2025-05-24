import type { ViewProps } from "react-native/Libraries/Components/View/ViewPropTypes";
import type { HostComponent } from "react-native";
import codegenNativeComponent from "react-native/Libraries/Utilities/codegenNativeComponent";
import type {
	Double,
	BubblingEventHandler,
	DirectEventHandler,
} from "react-native/Libraries/Types/CodegenTypes";

interface ChangeEvent {
	value: Double | null;
}

interface Range {
	lowerBound?: Double;
	upperBound?: Double;
}

interface Options {
	confirmText?: string;
	cancelText?: string;
	mode?: string;
}

export interface RTNDatePickerNativeProps extends ViewProps {
	type: string;
	isOpen: boolean;
	isInline: boolean;
	value: Double | null;
	onChange: BubblingEventHandler<Readonly<ChangeEvent>>;
	onConfirm: DirectEventHandler<null>;
	onCancel: DirectEventHandler<null>;
	range: Range;
	options: Options;
}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker",
	{ interfaceOnly: true }
) as HostComponent<RTNDatePickerNativeProps>;
