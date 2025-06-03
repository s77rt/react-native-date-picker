import {SafeAreaProvider} from 'react-native-safe-area-context';
import {NavigationContainer} from '@react-navigation/native';
import Home from './screens/Home';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import Date from './screens/Date';
import Datetime from './screens/Datetime';
import Time from './screens/Time';
import Yearmonth from './screens/Yearmonth';
import Mode from './screens/Mode';
import MinMax from './screens/MinMax';
import Step from './screens/Step';

const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <SafeAreaProvider>
        <Stack.Navigator>
          <Stack.Screen name="Home" component={Home} />
          <Stack.Screen name="Date" component={Date} />
          <Stack.Screen name="Datetime" component={Datetime} />
          <Stack.Screen name="Time" component={Time} />
          <Stack.Screen name="Yearmonth" component={Yearmonth} />
          <Stack.Screen name="Mode" component={Mode} />
          <Stack.Screen name="Min / Max" component={MinMax} />
          <Stack.Screen name="Step" component={Step} />
        </Stack.Navigator>
      </SafeAreaProvider>
    </NavigationContainer>
  );
}

export default App;
