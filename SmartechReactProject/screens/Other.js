import React, { Component } from 'react';
import { Alert, StyleSheet, TextInput, View, Platform } from 'react-native';
import Button from './styling/Button';

const NetcoreSDK = require("smartech-react-native");

export class Other extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      Token: '',
      Latitude: '',
      Longitute: ''
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <TextInput
          placeholder="Enter Token"
          onChangeText={Token => this.setState({ Token })}
          style={styles.input}
        />
        <Button onPress={this.callChangeToken}>Change Token</Button>
        <Button onPress={this.callGetToken}>Get New Token</Button>
        <Button onPress={this.callGetGUID}>Get GUID</Button>
        <TextInput
          placeholder="Enter Latitude"
          onChangeText={Latitude => this.setState({ Latitude })}
          style={styles.input}
        />
        <TextInput
          placeholder="Enter Longitute"
          onChangeText={Longitute => this.setState({ Longitute })}
          style={styles.input}
        />
        <Button onPress={this.callSetLocation}>Set Location</Button>
        <Button onPress={this.callGetNotification}>Get Notification</Button>
        <Button onPress={this.callGetUnreadNotificationsCount}>Get Unread Count</Button>
      </View>
    );
  }

  callSetLocation = () => {
    const nLatitude = parseFloat(this.state.Latitude);
    const nLongitute = parseFloat(this.state.Longitute);
    NetcoreSDK.setUserLocation(nLatitude, nLongitute);
  };
  callGetNotification = () => {
    if (Platform.OS === 'ios') {
      NetcoreSDK.getNotificationsiOS( value => {
        Alert.alert(
          'Result Data',
          value,
          [
            {
              text: 'Cancel',
              onPress: () => console.log('Cancel Pressed'),
              style: 'cancel'
            },
            {
              text: 'OK',
              onPress: () => console.log('Ok Pressed'),
              style: 'cancel'
            }
          ],
          { cancelable: false }
        );
      })
    } else {
      NetcoreSDK.getNotifications(10)
      .then(value => {
        value = JSON.stringify(value);
	console.log(value);
        Alert.alert(
          'Result Data',
          value,
          [
            {
              text: 'Cancel',
              onPress: () => console.log('Cancel Pressed'),
              style: 'cancel'
            },
            {
              text: 'OK',
              onPress: () => console.log('Ok Pressed'),
              style: 'cancel'
            }
          ],
          { cancelable: false }
        );
      })
      .catch(reason => console.log(reason));
    }
  };
  callChangeToken = () => {
    const newToken = this.state.Token;
    NetcoreSDK.setPushToken(newToken)
    .then(value => console.log(value))
    .catch(reason => console.log(reason));
  };
  callGetToken = () => {
    NetcoreSDK.getPushToken()
    .then(value => {
      Alert.alert(
        'New Token',
        value,
        [
          {
            text: 'Cancel',
            onPress: () => console.log('Cancel Pressed'),
            style: 'cancel'
          },
          {
            text: 'OK',
            onPress: () => console.log('Ok Pressed'),
            style: 'cancel'
          }
        ],
        { cancelable: false }
      );
    })
    .catch(reason => console.log(reason));
  };
  callGetGUID = () => {
    NetcoreSDK.getGUID()
    .then(value => {
      Alert.alert(
        'GUID',
        value,
        [
          {
            text: 'Cancel',
            onPress: () => console.log('Cancel Pressed'),
            style: 'cancel'
          },
          {
            text: 'OK',
            onPress: () => console.log('Ok Pressed'),
            style: 'cancel'
          }
        ],
        { cancelable: false }
      );
    })
    .catch(reason => console.log(reason));
  };
  callGetUnreadNotificationsCount = () => {
      NetcoreSDK.getUnreadNotificationsCount()
      .then(value => {
      console.log(value);
        Alert.alert(
          'UnreadNotificationsCount',
          value,
          [
            {
              text: 'Cancel',
              onPress: () => console.log('Cancel Pressed'),
              style: 'cancel'
            },
            {
              text: 'OK',
              onPress: () => console.log('Ok Pressed'),
              style: 'cancel'
            }
          ],
          { cancelable: false }
        );
      })
      .catch(reason => console.log(reason));
    };
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 24,
    textAlign: 'center',
    marginBottom: 40,
    fontWeight: 'bold'
  },
  input: {
    margin: 15,
    height: 40,
    borderColor: '#337ab7',
    borderWidth: 1,
    width: 250
  }
});

export default Other;
