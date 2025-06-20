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

function DebugYearmonth() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [log, setLog] = useState('');

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Time</Text>
      <Button
        title="Open modal"
        onPress={() => datePicker.current?.showPicker()}
      />
      <DatePicker
        ref={datePicker}
        type="yearmonth"
        value={selectedDate}
        onChange={date => {
          setLog(
            prevLog =>
              `${prevLog}\nonChange: ${date?.toLocaleString('default', {
                month: 'long',
                year: 'numeric',
              })}`,
          );
          setSelectedDate(date);
        }}
      />
      <DatePicker
        type="yearmonth"
        value={selectedDate}
        onChange={date => {
          setLog(
            prevLog =>
              `${prevLog}\nonChange: ${date?.toLocaleString('default', {
                month: 'long',
                year: 'numeric',
              })}`,
          );
          setSelectedDate(date);
        }}
        inline
      />

      <View>
        <Button
          title="Set yearmonth to April 2022"
          onPress={() => setSelectedDate(new Date('2022-04'))}
        />
        <Button
          title="Set yearmonth to August 2026"
          onPress={() => setSelectedDate(new Date('2026-08'))}
        />
        <Button title="Clear yearmonth" onPress={() => setSelectedDate(null)} />
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
    padding: 12,
    gap: 12,
  },
});

export default DebugYearmonth;
