import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function Locale() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Default</Text>
      <DatePicker type="date" inline />

      <View />
      <Text>ar-DZ</Text>
      <DatePicker type="date" options={{locale: 'ar-DZ'}} inline />

      <View />
      <Text>en-US</Text>
      <DatePicker type="date" options={{locale: 'en-US'}} inline />

      <View />
      <Text>es-ES</Text>
      <DatePicker type="date" options={{locale: 'es-ES'}} inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 12,
    gap: 12,
  },
});

export default Locale;
