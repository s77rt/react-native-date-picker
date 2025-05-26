<div align="center">
	<img alt="hero" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/hero.png" />
</div>

---

@s77rt/react-native-date-picker is a cross-platform date/time picker built with SwiftUI (iOS) and Jetpack Compose (Android).

-   📅 Date picker
-   🕜 Time picker
-   🔌 Supports iOS, Android and Web
-   💎 Renders in modal and inline
-   ✨ Highly customizable

---

|                                                                      iOS                                                                      |                                                                        Android                                                                        | mWeb - Safari                                                                                                                                                 | mWeb - Chrome                                                                                                                                                 | Web - Chrome                                                                                                                                                |
| :-------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | ------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <img alt="screenshot-ios" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-ios.png" /> | <img alt="screenshot-android" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-android.png" /> | <img alt="screenshot-mweb-safari" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-mweb-safari.png" /> | <img alt="screenshot-mweb-chrome" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-mweb-chrome.jpg" /> | <img alt="screenshot-web-chrome" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/screenshot-web-chrome.png" /> |

<details>
<summary>See it in action</summary>

https://github.com/user-attachments/assets/f46f5132-3385-48c9-8d1b-ee478b60fba8

</details>

## Installation

1. Install the package

    Using `npm`

    ```bash
    npm install @s77rt/react-native-date-picker
    ```

    Or `yarn`

    ```bash
    yarn add @s77rt/react-native-date-picker
    ```

2. Install pods (iOS)

    ```bash
    cd ios && pod install && cd ..
    ```

## Usage

```tsx
import { DatePicker } from "@s77rt/react-native-date-picker";
import type { DatePickerHandle } from "@s77rt/react-native-date-picker";
```

### Modal

<img align="right" width="100" height="auto" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/example-modal.png">

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

### Inline

<img align="right" width="100" height="auto" src="https://raw.githubusercontent.com/s77rt/react-native-date-picker/refs/heads/main/assets/example-inline.png">

```tsx
function Example() {
	const [selectedDate, setSelectedDate] = useState<Date | null>(null);

	return (
		<>
			<Text>Selected date: {selectedDate?.toLocaleDateString()}</Text>
			<DatePicker
				value={selectedDate}
				onChange={setSelectedDate}
				inline
			/>
		</>
	);
}
```

## Props

Inherits [View Props](https://reactnative.dev/docs/view#props).

| Prop       | Type                            | Description                                                                                        |
| ---------- | ------------------------------- | -------------------------------------------------------------------------------------------------- |
| `ref`      | `Ref<DatePickerHandle>`         | Ref for the date picker handle.                                                                    |
| `type`     | `"date" \| "time"`              | The type of the picker.                                                                            |
| `value`    | `Date \| null`                  | The selected date.                                                                                 |
| `onChange` | `(value: Date \| null) => void` | Callback when the user changes the selected date.                                                  |
| `min`      | `Date`                          | The earliest selectable date.                                                                      |
| `max`      | `Date`                          | The latest selectable date.                                                                        |
| `inline`   | `boolean`                       | Whether the date picker should be displayed inline. ⚫🟢                                           |
| `options`  | [`Options`](#options)           | Options. **Note:** Must be memoized ([`useMemo`](https://react.dev/reference/react/useMemo)). ⚫🟢 |
| `styles`   | [`Styles`](#styles)             | Styles. **Note:** Must be memoized ([`useMemo`](https://react.dev/reference/react/useMemo)). ⚫🟢  |

### Options

Various configuration options.

| Option           | Type                                  | Description                                 |
| ---------------- | ------------------------------------- | ------------------------------------------- |
| `confirmText`    | `string`                              | The confirm button text.                    |
| `cancelText`     | `string`                              | The cancel button text.                     |
| `mode`           | `"compact" \| "graphical" \| "wheel"` | The display mode. ⚫                        |
| `title`          | `string`                              | The title. 🟢                               |
| `headline`       | `string`                              | The headline. 🟢                            |
| `showModeToggle` | `boolean`                             | Whether the mode toggle should be shown. 🟢 |
| `is24Hour`       | `boolean`                             | Whether the time should be in 24-hour. 🟢   |

### Styles

Look and feel styles.

| Style                                    | Type         | Description                                   |
| ---------------------------------------- | ------------ | --------------------------------------------- |
| `accentColor`                            | `ColorValue` | The accent color. ⚫                          |
| `containerColor`                         | `ColorValue` | The container color. 🟢                       |
| `titleContentColor`                      | `ColorValue` | The title color. 🟢                           |
| `headlineContentColor`                   | `ColorValue` | The headline color. 🟢                        |
| `weekdayContentColor`                    | `ColorValue` | The weekday letters color. 🟢                 |
| `subheadContentColor`                    | `ColorValue` | The month and year subhead labels color. 🟢   |
| `navigationContentColor`                 | `ColorValue` | The year and arrow buttons color. 🟢          |
| `yearContentColor`                       | `ColorValue` | The year color. 🟢                            |
| `disabledYearContentColor`               | `ColorValue` | The disabled year color. 🟢                   |
| `currentYearContentColor`                | `ColorValue` | The current year color. 🟢                    |
| `selectedYearContentColor`               | `ColorValue` | The selected year color. 🟢                   |
| `disabledSelectedYearContentColor`       | `ColorValue` | The disabled selected year color. 🟢          |
| `selectedYearContainerColor`             | `ColorValue` | The selected year container color. 🟢         |
| `disabledSelectedYearContainerColor`     | `ColorValue` | The disabled selected container color. 🟢     |
| `dayContentColor`                        | `ColorValue` | The day color. 🟢                             |
| `disabledDayContentColor`                | `ColorValue` | The disabled day color. 🟢                    |
| `selectedDayContentColor`                | `ColorValue` | The selected day color. 🟢                    |
| `disabledSelectedDayContentColor`        | `ColorValue` | The disabled selected day color. 🟢           |
| `selectedDayContainerColor`              | `ColorValue` | The selected day container color. 🟢          |
| `disabledSelectedDayContainerColor`      | `ColorValue` | The disabled selected day container color. 🟢 |
| `todayContentColor`                      | `ColorValue` | The today color. 🟢                           |
| `todayDateBorderColor`                   | `ColorValue` | The today border color. 🟢                    |
| `dayInSelectionRangeContainerColor`      | `ColorValue` | The selected days container color. 🟢         |
| `dayInSelectionRangeContentColor`        | `ColorValue` | The selected days color. 🟢                   |
| `dividerColor`                           | `ColorValue` | The divider color. 🟢                         |
| `clockDialColor`                         | `ColorValue` | The clock dial color. 🟢                      |
| `selectorColor`                          | `ColorValue` | The clock dial selector color. 🟢             |
| `periodSelectorBorderColor`              | `ColorValue` | The period selector border color. 🟢          |
| `clockDialSelectedContentColor`          | `ColorValue` | The selected number color. 🟢                 |
| `clockDialUnselectedContentColor`        | `ColorValue` | The unselected number color. 🟢               |
| `periodSelectorSelectedContainerColor`   | `ColorValue` | The selected period container color. 🟢       |
| `periodSelectorUnselectedContainerColor` | `ColorValue` | The unselected period container color. 🟢     |
| `periodSelectorSelectedContentColor`     | `ColorValue` | The selected period color. 🟢                 |
| `periodSelectorUnselectedContentColor`   | `ColorValue` | The unselected period color. 🟢               |
| `timeSelectorSelectedContainerColor`     | `ColorValue` | The selected time container color. 🟢         |
| `timeSelectorUnselectedContainerColor`   | `ColorValue` | The unselected time container color. 🟢       |
| `timeSelectorSelectedContentColor`       | `ColorValue` | The selected time color. 🟢                   |
| `timeSelectorUnselectedContentColor`     | `ColorValue` | The unselected time color. 🟢                 |

## Methods

Imperative handle methods.

| Method       | Type         | Description       |
| ------------ | ------------ | ----------------- |
| `showPicker` | `() => void` | Shows the picker. |

## Feedback

Every code review, bug report and feature request is appreciated! Please feel free to [share your feedback](https://github.com/s77rt/react-native-date-picker/issues/new).

## License

[MIT](LICENSE)

---

⚫ iOS - 🟢 Android - ⚫🟢 iOS/Android
