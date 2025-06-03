import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function MinMax() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Disable past dates</Text>
      <DatePicker type="date" min={new Date()} inline />

      <View />
      <Text>Disable future dates</Text>
      <DatePicker type="date" max={new Date()} inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 12,
    gap: 12,
  },
});

export default MinMax;
