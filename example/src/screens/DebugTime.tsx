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

function DebugTime() {
  const datePicker = useRef<DatePickerHandle>(null);
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
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
        type="time"
        value={selectedDate}
        onChange={date => {
          setLog(prevLog => `${prevLog}\nonChange: ${date?.toString()}`);
          setSelectedDate(date);
        }}
      />
      <DatePicker
        type="time"
        value={selectedDate}
        onChange={date => {
          setLog(prevLog => `${prevLog}\nonChange: ${date?.toString()}`);
          setSelectedDate(date);
        }}
        inline
      />

      <View>
        <Button
          title="Set time to 09:00"
          onPress={() =>
            setSelectedDate(new Date(new Date().setHours(9, 0, 0, 0)))
          }
        />
        <Button
          title="Set time to 21:35"
          onPress={() =>
            setSelectedDate(new Date(new Date().setHours(21, 35, 0, 0)))
          }
        />
        <Button title="Clear time" onPress={() => setSelectedDate(null)} />
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

export default DebugTime;
