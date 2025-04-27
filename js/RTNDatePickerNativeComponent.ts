import type { ViewProps } from "ViewPropTypes";
import type { HostComponent } from "react-native";
import codegenNativeComponent from "react-native/Libraries/Utilities/codegenNativeComponent";

export interface RTNDatePickerNativeProps extends ViewProps {}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker"
) as HostComponent<RTNDatePickerNativeProps>;
