import type { ViewProps } from "ViewPropTypes";
import type { HostComponent } from "react-native";
import codegenNativeComponent from "react-native/Libraries/Utilities/codegenNativeComponent";
import type {
	BubblingEventHandler,
	DirectEventHandler,
} from "react-native/Libraries/Types/CodegenTypes";

export interface RTNDatePickerNativeProps extends ViewProps {
	isOpen: boolean;
	onChange: BubblingEventHandler<null>;
	onConfirm: DirectEventHandler<null>;
	onCancel: DirectEventHandler<null>;
}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker"
) as HostComponent<RTNDatePickerNativeProps>;
