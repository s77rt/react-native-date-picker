import type { ViewProps } from "react-native/Libraries/Components/View/ViewPropTypes";
import type { HostComponent } from "react-native";
import codegenNativeComponent from "react-native/Libraries/Utilities/codegenNativeComponent";
import type {
	Double,
	BubblingEventHandler,
	DirectEventHandler,
} from "react-native/Libraries/Types/CodegenTypes";

export interface ChangeEvent {
	value: Double | null;
}

export interface Range {
	lowerBound?: Double;
	upperBound?: Double;
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
}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker"
) as HostComponent<RTNDatePickerNativeProps>;
