import {
  StyleSheet,
  Text,
  ScrollView,
  View,
  Button,
  Pressable,
} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';
import type {DatePickerHandle} from '@s77rt/react-native-date-picker';
import {useRef, useState} from 'react';

function DebugMultipleDates() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDates, setSelectedDates] = useState<Date[]>([]);
  const [log, setLog] = useState('');

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Date</Text>
      <Button
        title="Open modal"
        onPress={() => datePicker.current?.showPicker()}
      />
      <DatePicker
        ref={datePicker}
        type="date"
        multiple
        value={selectedDates}
        onChange={dates => {
          setLog(prevLog => `${prevLog}\nonChange: ${dates?.toString()}`);
          setSelectedDates(dates);
        }}
      />
      <DatePicker
        type="date"
        multiple
        value={selectedDates}
        onChange={dates => {
          setLog(prevLog => `${prevLog}\nonChange: ${dates?.toString()}`);
          setSelectedDates(dates);
        }}
        inline
      />

      <View>
        <Button
          title="Set dates to 2025-04-16, 2025-04-18"
          onPress={() =>
            setSelectedDates([new Date('2025-04-16'), new Date('2025-04-18')])
          }
        />
        <Button
          title="Set date to 1746227970959, 1746476329000"
          onPress={() =>
            setSelectedDates([new Date(1746227970959), new Date(1746476329000)])
          }
        />
        <Button title="Clear dates" onPress={() => setSelectedDates([])} />
      </View>

      <View>
        <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
          <Text>Log</Text>
          <Pressable onPress={() => setLog('')}>
            <Text>Clear</Text>
          </Pressable>
        </View>
        <Text>{log}</Text>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 12,
    gap: 12,
  },
});

export default DebugMultipleDates;
