import {
  StyleSheet,
  Text,
  ScrollView,
  View,
  Button,
  Pressable,
} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';
import {useState} from 'react';

function DebugDate() {
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [log, setLog] = useState('');

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Date</Text>
      <DatePicker
        type="date"
        value={selectedDate}
        onChange={date => {
          setLog(prevLog => `${prevLog}\nonChange: ${date.toString()}`);
          setSelectedDate(date);
        }}
        inline
      />

      <View>
        <Button
          title="Set date to 2025-04-16"
          onPress={() => setSelectedDate(new Date('2025-04-16'))}
        />
        <Button
          title="Set date to 1746227970959"
          onPress={() => setSelectedDate(new Date(1746227970959))}
        />
        <Button title="Clear date" onPress={() => setSelectedDate(null)} />
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

export default DebugDate;
