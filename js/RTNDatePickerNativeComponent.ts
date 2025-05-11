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

export interface RTNDatePickerNativeProps extends ViewProps {
	isOpen: boolean;
	value: Double | null;
	onChange: BubblingEventHandler<Readonly<ChangeEvent>>;
	onConfirm: DirectEventHandler<null>;
	onCancel: DirectEventHandler<null>;
}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker"
) as HostComponent<RTNDatePickerNativeProps>;
