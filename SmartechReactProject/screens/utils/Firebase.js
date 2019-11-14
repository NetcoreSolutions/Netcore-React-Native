import React, {Component} from 'react';
import { AsyncStorage, Text, View, Alert } from 'react-native';
import firebase from 'react-native-firebase';
const NetcoreSDK = require("smartech-react-native");

export default class Firebase extends Component {

async componentDidMount() {
  this.createNotificationListeners();
}
//Remove listeners allocated in createNotificationListeners()
componentWillUnmount() {
  this.notificationListener();
  this.notificationOpenedListener();
}
async createNotificationListeners() {
  /*
  * Triggered when a particular notification has been received in foreground
  * */
  console.log("createNotificationListeners1");
  this.notificationListener = firebase.notifications().onNotification((notification) => {
  console.log("createNotificationListeners2");
      const { title, body } = notification;
      this.showAlert(title, body);
  });

  /*
  * If your app is in background, you can listen for when a notification is clicked / tapped / opened as follows:
  * */
  this.notificationOpenedListener = firebase.notifications().onNotificationOpened((notificationOpen) => {
  console.log("createNotificationListeners3");
      const { title, body } = notificationOpen.notification;
      this.showAlert(title, body);
  });

  /*
  * If your app is closed, you can check if it was opened by a notification being clicked / tapped / opened as follows:
  * */
  const notificationOpen = await firebase.notifications().getInitialNotification();
  if (notificationOpen) {
  console.log("createNotificationListeners4");
      const { title, body } = notificationOpen.notification;
  }
  /*
  * Triggered for data only payload in foreground
  * */
  this.messageListener = firebase.messaging().onMessage((message) => {
  console.log("createNotificationListeners5");
    //process data message
    console.log(JSON.stringify(message));
    var parsedData = JSON.parse(JSON.stringify(message));
    //console.log(JSON.stringify(parsedData._data));
    this.callHandleNotification(JSON.stringify(parsedData._data));

  });
}

callHandleNotification(remotedata) {
  console.log("remotedata"+remotedata);
  let user = {     // an object
    trid: "71564-588-6-0-20190917123131-T",
    data: "{\"image\":\"\",\"sound\":true,\"deeplink\":\"\",\"actionButton\":[],\"expiry\":1571122891,\"carousel\":[],\"title\":\"Title123\",\"message\":\"Message123\",\"customPayload\":{},\"trid\":\"71564-588-6-0-20190917123131-T\"}"
    };
      NetcoreSDK.handleNotification(user)
      .then(value => {
        console.log('handle-----'+value);
      })
      .catch(reason => console.log(reason));
    };
  render() {
    return (
      <View style={{flex: 1}}>
        <Text>Welcome to React Native!</Text>
      </View>
    );
  }
}