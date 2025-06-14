import {StyleSheet, Text, ScrollView, Button, View} from 'react-native';
import {DatePicker} from '@s77rt/react-native-date-picker';

function StyledTime() {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Styled time</Text>
      <DatePicker
        type="time"
        styles={{
          accentColor: 'green',
          containerColor: 'limegreen',
          clockDialColor: 'black',
          selectorColor: 'white',
          clockDialSelectedContentColor: 'black',
          clockDialUnselectedContentColor: 'white',
          timeSelectorSelectedContainerColor: 'yellow',
          timeSelectorUnselectedContainerColor: 'lightgray',
        }}
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

export default StyledTime;
