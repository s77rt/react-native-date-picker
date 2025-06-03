import {
  StyleSheet,
  Text,
  ScrollView,
  TouchableOpacity,
  View,
} from 'react-native';

function Home({navigation}: {navigation: any}) {
  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text>Basic</Text>

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Date')}>
        <Text style={styles.buttonText}>Date</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Datetime')}>
        <Text style={styles.buttonText}>Datetime</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Time')}>
        <Text style={styles.buttonText}>Time</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Yearmonth')}>
        <Text style={styles.buttonText}>Yearmonth</Text>
      </TouchableOpacity>

      <View />
      <Text>Features</Text>

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Mode')}>
        <Text style={styles.buttonText}>Mode</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Min / Max')}>
        <Text style={styles.buttonText}>Min / Max</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Step')}>
        <Text style={styles.buttonText}>Step</Text>
      </TouchableOpacity>

      <View />
      <Text>Options</Text>

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Datetime')}>
        <Text style={styles.buttonText}>Title & Headline</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Datetime')}>
        <Text style={styles.buttonText}>Time 24H</Text>
      </TouchableOpacity>

      <View />
      <Text>Styles</Text>

      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Datetime')}>
        <Text style={styles.buttonText}>Styled Date</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.button}
        onPress={() => navigation.navigate('Datetime')}>
        <Text style={styles.buttonText}>Styled Time</Text>
      </TouchableOpacity>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 12,
    gap: 12,
  },
  button: {
    backgroundColor: 'white',
    borderRadius: 4,
    padding: 8,
    width: '100%',
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.2,
    shadowRadius: 1.41,
    elevation: 2,
  },
  buttonText: {
    fontWeight: 'bold',
    color: 'black',
    textAlign: 'center',
  },
});

export default Home;
