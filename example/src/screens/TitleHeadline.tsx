import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function TitleHeadline() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Custom title and headline</Text>
      <DatePicker
        type="date"
        options={{title: 'Custom title', headline: 'Custom headline'}}
        inline
      />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 12,
    gap: 12,
  },
});

export default TitleHeadline;
