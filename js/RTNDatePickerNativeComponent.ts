import type { ViewProps } from "react-native/Libraries/Components/View/ViewPropTypes";
import type { HostComponent, ColorValue } from "react-native";
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

interface Styles {
	accentColor?: ColorValue;
	containerColor?: ColorValue;
	titleContentColor?: ColorValue;
	headlineContentColor?: ColorValue;
	weekdayContentColor?: ColorValue;
	subheadContentColor?: ColorValue;
	navigationContentColor?: ColorValue;
	yearContentColor?: ColorValue;
	disabledYearContentColor?: ColorValue;
	currentYearContentColor?: ColorValue;
	selectedYearContentColor?: ColorValue;
	disabledSelectedYearContentColor?: ColorValue;
	selectedYearContainerColor?: ColorValue;
	disabledSelectedYearContainerColor?: ColorValue;
	dayContentColor?: ColorValue;
	disabledDayContentColor?: ColorValue;
	selectedDayContentColor?: ColorValue;
	disabledSelectedDayContentColor?: ColorValue;
	selectedDayContainerColor?: ColorValue;
	disabledSelectedDayContainerColor?: ColorValue;
	todayContentColor?: ColorValue;
	todayDateBorderColor?: ColorValue;
	dayInSelectionRangeContainerColor?: ColorValue;
	dayInSelectionRangeContentColor?: ColorValue;
	dividerColor?: ColorValue;
	clockDialColor?: ColorValue;
	selectorColor?: ColorValue;
	periodSelectorBorderColor?: ColorValue;
	clockDialSelectedContentColor?: ColorValue;
	clockDialUnselectedContentColor?: ColorValue;
	periodSelectorSelectedContainerColor?: ColorValue;
	periodSelectorUnselectedContainerColor?: ColorValue;
	periodSelectorSelectedContentColor?: ColorValue;
	periodSelectorUnselectedContentColor?: ColorValue;
	timeSelectorSelectedContainerColor?: ColorValue;
	timeSelectorUnselectedContainerColor?: ColorValue;
	timeSelectorSelectedContentColor?: ColorValue;
	timeSelectorUnselectedContentColor?: ColorValue;
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
	styles: Styles;
}

export default codegenNativeComponent<RTNDatePickerNativeProps>(
	"RTNDatePicker",
	{ interfaceOnly: true }
) as HostComponent<RTNDatePickerNativeProps>;
