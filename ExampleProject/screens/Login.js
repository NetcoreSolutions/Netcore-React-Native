import React, { Component } from "react";
import {Alert, AsyncStorage, StyleSheet, Text, TextInput, View } from "react-native";
import Button from "./styling/Button";
const NetcoreSDK = require("smartech-react-native");

export class Login extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      Identity: "",
      UserIdentity: ""
    };
  }
  componentDidMount() {
    AsyncStorage.getItem("UserIdentity")
      .then(value => {
        this.setState({ UserIdentity: value });
      })
      .done();
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Login</Text>
        <TextInput
          placeholder="Enter Identity"
          onChangeText={Identity => this.setState({ Identity })}
          style={styles.input}
        />
        <View>{this.checkIdentity()}</View>
        <View>{this.getCustomData()}</View>
        <Button onPress={this.setIdentity}>Log In</Button>
        <Button onPress={this.setSkip}>Skip</Button>
      </View>
    );
  }
  checkIdentity = () => {
    try {
      const email = this.state.UserIdentity;
      console.log("UserIdentity->" + email);
      if (email !== "" && email != null) {
        this.setSkip();
      }
    } catch (error) {
      // Error retrieving data
      console.log("Error->" + error.message);
    }
  };
  setIdentity = () => {
    const email = this.state.Identity;
    NetcoreSDK.login(email)
      .then(result => {
        console.log(result);
      })
      .catch(reason => console.log(reason));

    AsyncStorage.setItem("UserIdentity", email);
    Alert.alert(
      "Hello",
      email,
      [
        {
          text: "Cancel",
          onPress: () => console.log("Cancel Pressed"),
          style: "cancel"
        },
        { text: "OK", onPress: () => this.setSkip() }
      ],
      { cancelable: false }
    );
  };
  setSkip = () => {
    const { navigate } = this.props.navigation;
    navigate("HomeScreen", { name: "Home Screen" });
  };
 
  getCustomData = () => {
    console.log("getCustomData->" + JSON.stringify(this.props));
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
  input: {
    margin: 15,
    height: 40,
    borderColor: "#337ab7",
    borderWidth: 1,
    width: 250
  }
});

export default Login;
