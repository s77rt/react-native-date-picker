import {useState, useRef} from 'react';
import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';
import type {DatePickerHandle} from '@s77rt/react-native-date-picker';

function Date() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);

  return (
    <>
      <Text style={styles.selectedDate}>
        <Text>Selection:</Text>
        {'\n'}
        {selectedDate?.toLocaleDateString() ?? '(none)'}
      </Text>
      <ScrollView contentContainerStyle={styles.container}>
        <Text>Modal</Text>
        <Button
          title="Open modal"
          onPress={() => datePicker.current?.showPicker()}
        />
        <DatePicker
          ref={datePicker}
          type="date"
          value={selectedDate}
          onChange={setSelectedDate}
        />

        <View />
        <Text>Inline</Text>
        <DatePicker
          type="date"
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
    margin: 12,
    textAlign: 'center',
  },
  container: {
    margin: 12,
    gap: 12,
  },
});

export default Date;
