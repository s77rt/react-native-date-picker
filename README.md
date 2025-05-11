<div align="center">
	<img alt="hero" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/hero.png" />
</div>

---

@s77rt/react-native-date-picker is a cross-platform date picker built with SwiftUI (iOS) and Jetpack Compose (Android).

## Installation

```bash
npm install @s77rt/react-native-date-picker
```

## Demo

| iOS                                                                                                                           | Android                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| <img alt="hero" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/demo-ios.gif" /> | <img alt="hero" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/demo-android.gif" /> |

## Usage

```tsx
import { DatePicker } from "@s77rt/react-native-date-picker";
import type { DatePickerHandle } from "@s77rt/react-native-date-picker";
```

```tsx
function Example() {
	const datePicker = useRef<DatePickerHandle>(null);
	const [selectedDate, setSelectedDate] = useState<Date | null>(null);

	return (
		<>
			<Text>Selected date: {selectedDate?.toLocaleDateString()}</Text>
			<View>
				<Button
					title="Select date 📅"
					onPress={() => datePicker.current?.showPicker()}
				/>
				<DatePicker
					ref={datePicker}
					value={selectedDate}
					onChange={setSelectedDate}
				/>
			</View>
		</>
	);
}
```

## Props

| Prop       | Type                            | Description                                       |
| ---------- | ------------------------------- | ------------------------------------------------- |
| `ref`      | `Ref<DatePickerHandle>`         | Ref for the date picker handle.                   |
| `value`    | `Date \| null`                  | The selected date.                                |
| `onChange` | `(value: Date \| null) => void` | Callback when the user changes the selected date. |

## Methods

| Method       | Type         | Description       |
| ------------ | ------------ | ----------------- |
| `showPicker` | `() => void` | Shows the picker. |

## License

[MIT](LICENSE)
