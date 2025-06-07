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
import TitleHeadline from './screens/TitleHeadline';
import Time24H from './screens/Time24H';
import StyledDate from './screens/StyledDate';
import StyledTime from './screens/StyledTime';
import Locale from './screens/Locale';
import DebugDate from './screens/DebugDate';

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
          <Stack.Screen name="Locale" component={Locale} />
          <Stack.Screen name="Mode" component={Mode} />
          <Stack.Screen name="Min / Max" component={MinMax} />
          <Stack.Screen name="Step" component={Step} />
          <Stack.Screen name="Title & Headline" component={TitleHeadline} />
          <Stack.Screen name="Time 24H" component={Time24H} />
          <Stack.Screen name="Styled Date" component={StyledDate} />
          <Stack.Screen name="Styled Time" component={StyledTime} />
          <Stack.Screen name="Debug Date" component={DebugDate} />
        </Stack.Navigator>
      </SafeAreaProvider>
    </NavigationContainer>
  );
}

export default App;
