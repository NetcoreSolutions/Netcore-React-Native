import React, { Component } from "react";
import { AsyncStorage, ScrollView, StyleSheet, Text, View } from "react-native";
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

          <Button onPress={this.callOther}>Other Functions</Button>
          <Button onPress={this.callCustom}>Custom Data</Button>

          <Button onPress={this.callLogOut}>Log Out</Button>
          <Text style={styles.welcome} />
        </ScrollView>
      </View>
    );
  }
  callPageBrowse = () => {
    console.log("data->", payloadata);
    NetcoreSDK.track("Page Browse", payloadata);
  };
  callCheckOut = () => {
    console.log("data->", payloadata);
    NetcoreSDK.track("Checkout", payloadata);
  };
  callCartExpired = () => {
    console.log("data->", payloadata);
   NetcoreSDK.track("Cart Expired", payloadata);
  };

  callAddToCart = () => {
    console.log("callAddToCart->", payloadata);
    NetcoreSDK.track("Add To Cart", payloadata);
  };
  callRemoveFromCart = () => {
    console.log("data->", payloadata);
    NetcoreSDK.track("Remove From Cart", payloadata);
  };

  callOPTIn = () => {
    NetcoreSDK.optOut(false);
  };
  callOPTOut = () => {
    NetcoreSDK.optOut(true);
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
  callLogOut = () => {
    const { navigate } = this.props.navigation;
    NetcoreSDK.logout();
    NetcoreSDK.clearIdentity();
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
