import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function StyledDate() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Styled date</Text>
      <DatePicker
        type="date"
        styles={{
          accentColor: 'green',
          containerColor: 'limegreen',
          navigationContentColor: 'white',
          dayContentColor: 'black',
          selectedDayContentColor: 'white',
          selectedDayContainerColor: 'black',
          todayContentColor: 'black',
          todayDateBorderColor: 'pink',
        }}
        inline
      />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 12,
    gap: 12,
  },
});

export default StyledDate;
