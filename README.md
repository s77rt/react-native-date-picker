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
```

```tsx
function Example() {
	const [isDatePickerOpen, setIsDatePickerOpen] = useState(false);
	const [displayedDate, setDisplayedDate] = useState<Date>();
	const [selectedDate, setSelectedDate] = useState<Date>();

	return (
		<>
			<Text>Selected date: {selectedDate?.toString()}</Text>
			<Button
				title="Select date 📅"
				onPress={() => setIsDatePickerOpen(true)}
			/>
			<DatePicker
				isOpen={isDatePickerOpen}
				value={displayedDate}
				onChange={setDisplayedDate}
				onConfirm={() => {
					setSelectedDate(displayedDate);
					setIsDatePickerOpen(false);
				}}
				onCancel={() => {
					setDisplayedDate(selectedDate);
					setIsDatePickerOpen(false);
				}}
			/>
		</>
	);
}
```

## Props

| Prop        | Type                    | Default      | Description                                                            |
| ----------- | ----------------------- | ------------ | ---------------------------------------------------------------------- |
| `ìsOpen`    | `boolean`               | `false`      | Whether the date picker modal is open                                  |
| `value`     | `Date`                  | `new Date()` | The set date in the date picker                                        |
| `onChange`  | `(value: Date) => void` |              | Callback when the user changes the date                                |
| `onConfirm` | `() => void`            |              | Callback when the user clicks the confirm button                       |
| `onCancel`  | `() => void`            |              | Callback when the user clicks the cancel button or dismisses the modal |

## License

[MIT](LICENSE)
