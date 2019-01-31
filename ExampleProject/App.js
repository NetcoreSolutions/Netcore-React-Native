/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';

import { createAppContainer, createStackNavigator } from 'react-navigation';
import Login from './screens/Login';
import Home from './screens/Home';
import Profile from './screens/Profile';
import Other from './screens/Other';
import Custom from './screens/Custom';

const AppNavigator = createStackNavigator({
  LoginScreen: { screen: Login },
  HomeScreen: { screen: Home },
  ProfileScreen: { screen: Profile },
  OtherScreen: { screen: Other },
  CustomScreen: { screen: Custom }
});
const App = createAppContainer(AppNavigator);

export default App;