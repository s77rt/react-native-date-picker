import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';
import type {DatePickerHandle} from '@s77rt/react-native-date-picker';
import {useRef, useState} from 'react';

function Multiple() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDates, setSelectedDates] = useState<Date[]>([]);

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.selectedDates}>
        <Text>Selection:</Text>
        {'\n'}
        {selectedDates?.map(date => date.toLocaleDateString()).join(', ') ??
          '(none)'}
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
          value={selectedDates}
          onChange={setSelectedDates}
          multiple
        />

        <View />
        <Text>Inline</Text>
        <DatePicker
          type="date"
          value={selectedDates}
          onChange={setSelectedDates}
          multiple
          inline
        />
      </ScrollView>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  selectedDates: {
    padding: 12,
    textAlign: 'center',
  },
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Multiple;
