import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function Step() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Time 15 minutes interval</Text>
      <DatePicker type="time" step={60 * 15} inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Step;
