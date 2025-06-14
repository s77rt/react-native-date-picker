import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function Mode() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Compact</Text>
      <DatePicker type="date" options={{mode: 'compact'}} inline />

      <View />
      <Text>Graphical</Text>
      <DatePicker type="date" options={{mode: 'graphical'}} inline />

      <View />
      <Text>Wheel</Text>
      <DatePicker type="date" options={{mode: 'wheel'}} inline />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 12,
    gap: 12,
  },
});

export default Mode;
