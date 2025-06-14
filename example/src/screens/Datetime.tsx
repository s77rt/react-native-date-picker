import {useState, useRef} from 'react';
import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';
import type {DatePickerHandle} from '@s77rt/react-native-date-picker';

function Datetime() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);

  return (
    <>
      <Text style={styles.selectedDate}>
        <Text>Selection:</Text>
        {'\n'}
        {selectedDate?.toLocaleString() ?? '(none)'}
      </Text>
      <ScrollView contentContainerStyle={styles.container}>
        <Text>Modal</Text>
        <Button
          title="Open modal"
          onPress={() => datePicker.current?.showPicker()}
        />
        <DatePicker
          ref={datePicker}
          type="datetime"
          value={selectedDate}
          onChange={setSelectedDate}
        />

        <View />
        <Text>Inline</Text>
        <DatePicker
          type="datetime"
          value={selectedDate}
          onChange={setSelectedDate}
          inline
        />
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  selectedDate: {
    padding: 12,
    textAlign: 'center',
  },
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Datetime;
