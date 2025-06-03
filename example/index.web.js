import {AppRegistry} from 'react-native';
import App from './src/App';
import AppInfo from './app.json';

AppRegistry.registerComponent(AppInfo.name, () => App);

AppRegistry.runApplication(AppInfo.name, {
  rootTag: document.getElementById('root'),
});
