import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function Multiple() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Multiple selection</Text>
      <DatePicker type="date" multiple inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Multiple;
