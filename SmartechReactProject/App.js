/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import { Alert, NativeEventEmitter, NativeModules } from 'react-native';

import { createStackNavigator } from 'react-navigation-stack';
import { createAppContainer } from 'react-navigation';
import Login from './screens/Login';
import Home from './screens/Home';
import Profile from './screens/Profile';
import Other from './screens/Other';
import Custom from './screens/Custom';
import Notifications from './screens/Notifications';

const AppNavigator = createStackNavigator({
  LoginScreen: { screen: Login },
  HomeScreen: { screen: Home },
  ProfileScreen: { screen: Profile, path: 'profile'},
  OtherScreen: { screen: Other },
  CustomScreen: { screen: Custom },
  NotificationsScreen : {screen: Notifications }
});
const AppContainer = createAppContainer(AppNavigator);
const { DeeplinkManager } = NativeModules;
const deeplinkManagerEmitter = new NativeEventEmitter(DeeplinkManager);

export default class App extends React.Component {
   render() {
        return(<AppContainer screenProps={this.props} />);
   }

   componentDidMount() {
    deeplinkManagerEmitter.addListener('SMTDeeplink', this.handleDeeplink);
	}

	componentWillUnmount() {
    deeplinkManagerEmitter.removeEventListener('SMTDeeplink', this.handleDeeplink);
	}

	handleDeeplink(event) {
    console.log("Smartech: deeplink: " + event.deeplink);
    console.log("Smartech: customPayload: " + event.customPayload);
    // console.log("Smartech: this.props: " + JSON.stringify(this.props));
    // const {navigate} = this.props.navigation;
    // if (event.deeplink == "smartech://image") {
    //   navigate('Profile');
    // }
    // if (event.deeplink == "smartech://custom") {
    //   navigate('Custom');
    // }
	}
}