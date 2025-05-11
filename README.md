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

### Screenshots

|                                                                      iOS                                                                      |                                                                        Android                                                                        | mWeb - Safari                                                                                                                                                 | mWeb - Chrome                                                                                                                                                 | Web - Chrome                                                                                                                                                |
| :-------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | ------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <img alt="screenshot-ios" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-ios.png" /> | <img alt="screenshot-android" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-android.png" /> | <img alt="screenshot-mweb-safari" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-mweb-safari.png" /> | <img alt="screenshot-mweb-chrome" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-mweb-chrome.jpg" /> | <img alt="screenshot-web-chrome" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-web-chrome.png" /> |

<details>
<summary>See it in action</summary>

<video alt="demo" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/demo.mp4" />

</details>

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
