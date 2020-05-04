import React, { Component } from "react";
import { AsyncStorage, ScrollView, StyleSheet, Text, View, Alert} from "react-native";
import Button from "./styling/Button";

const NetcoreSDK = require("smartech-react-native");

const payloadata = {
    name: "Galaxy",
    description: "20gram bars",
    payload_id: "1",
    event_id:21
};

export class Home extends Component<Props> {
  render() {
    return (
      <View style={styles.container}>
        <ScrollView>
          <Text style={styles.welcome}>Welcome to Netcore!</Text>

          <Button onPress={this.callPageBrowse}>Page Browse</Button>
          <Button onPress={this.callCheckOut}>Check Out</Button>
          <Button onPress={this.callCartExpired}>Cart Expired</Button>

          <Button onPress={this.callAddToCart}>Add To Cart</Button>
          <Button onPress={this.callRemoveFromCart}>Remove From Cart</Button>
          <Button onPress={this.callProfile}>Update Profile</Button>

          <Button onPress={this.callOPTIn}>OPT In</Button>
          <Button onPress={this.callOPTOut}>OPT Out</Button>
	      <Button onPress={this.callOPTStatus}>Get Opt Status</Button>
	      <Button onPress={this.callGetUserIdentity}>Get UserIdentity</Button>

          <Button onPress={this.callOther}>Other Functions</Button>
          <Button onPress={this.callCustom}>Custom Data</Button>
          <Button onPress={this.callLogOut}>Log Out</Button>
          <Text style={styles.welcome} />
        </ScrollView>
      </View>
    );
  }
  callPageBrowse = () => {
    this.trackEvent("Page Browse", payloadata)

  };
  callCheckOut = () => {
    this.trackEvent("Checkout", payloadata)
  };
  callCartExpired = () => {
    this.trackEvent("Cart Expired", payloadata)
  };

  callAddToCart = () => {
    this.trackEvent("Add To Cart", payloadata)
  };
  callRemoveFromCart = () => {
    this.trackEvent("Remove From Cart", payloadata)
  };

  trackEvent = (eventName, payloadata) => {
    	console.log("Name->", eventName);
    	console.log("data->", payloadata);
	    NetcoreSDK.trackEvent(eventName, payloadata);
  };

  callOPTIn = () => {
	NetcoreSDK.optOut(false);
  };
  callOPTOut = () => {
	NetcoreSDK.optOut(true);
  };
  callOPTStatus = () => {
	NetcoreSDK.getOptOutStatus()
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
  };

    callGetUserIdentity = () => {
	NetcoreSDK.getUserIdentity()
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
  };

  callProfile = () => {
    const { navigate } = this.props.navigation;
    navigate("ProfileScreen", { name: "Profile Screen" });
  };

  callOther = () => {
    const { navigate } = this.props.navigation;
    navigate("OtherScreen", { name: "Other Screen" });
  };
  callCustom = () => {
    const { navigate } = this.props.navigation;
    navigate("CustomScreen", { name: "Custom Screen" });
  };
  callNotifications = () => {
    const { navigate } = this.props.navigation;
    navigate("NotificationsScreen", { name: "Notifications Screen" });
  };
  callLogOut = () => {
    const { navigate } = this.props.navigation;
    NetcoreSDK.logoutAndClearUserIdentity(true);
//    NetcoreSDK.logout();
    AsyncStorage.setItem("UserIdentity", "");
    navigate("LoginScreen", { name: "Login Screen" });
  };
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  },
  welcome: {
    fontSize: 24,
    textAlign: "center",
    marginBottom: 40,
    fontWeight: "bold"
  },
  instructions: {
    fontSize: 12,
    textAlign: "center",
    color: "#333333",
    marginBottom: 5
  },
  headerText: {
    backgroundColor: "rgba(92, 99,216, 1)",
    width: 300,
    height: 45,
    borderColor: "transparent",
    borderWidth: 0,
    borderRadius: 5
  }
});

export default Home;
