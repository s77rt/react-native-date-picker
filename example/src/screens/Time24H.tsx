import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function Time24H() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>24 hours clock</Text>
      <DatePicker type="time" options={{is24Hour: true}} inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Time24H;
