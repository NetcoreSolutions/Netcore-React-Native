import React, { Component } from "react";
import {
  Alert,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View
} from "react-native";
import Button from "./styling/Button";

const NetcoreSDK = require("smartech-react-native");

export class Profile extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      NAME: "",
      EMAILID: "",
      AGE: "",
      MOBILENO: "",
      DOB: "",
      SALARY: "",
      WEBSITE: ""
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <ScrollView>
          <Text style={styles.welcome}>Profile Details</Text>
          <TextInput
            placeholder="ENTER NAME"
            onChangeText={NAME => this.setState({ NAME })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER EMAILID"
            onChangeText={EMAILID => this.setState({ EMAILID })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER AGE"
            onChangeText={AGE => this.setState({ AGE })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER MOBILENO"
            onChangeText={MOBILENO => this.setState({ MOBILENO })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER DOB"
            onChangeText={DOB => this.setState({ DOB })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER SALARY"
            onChangeText={SALARY => this.setState({ SALARY })}
            style={styles.input}
          />

          <TextInput
            placeholder="ENTER WEBSITE"
            onChangeText={WEBSITE => this.setState({ WEBSITE })}
            style={styles.input}
          />

          <Button onPress={this.save}>Save</Button>
        </ScrollView>
      </View>
    );
  }

  save = () => {
    console.log("save");
    const NAME = this.state.NAME;
    const EMAILID = this.state.EMAILID;
    const AGE = this.state.AGE;
    const MOBILENO = this.state.MOBILENO;
    const DOB = this.state.DOB;
    const SALARY = this.state.SALARY;
    const WEBSITE = this.state.WEBSITE;

    const { navigate } = this.props.navigation;

    const payloadata = {
      NAME: NAME,
      EMAILID: EMAILID,
      AGE: AGE,
      MOBILENO: MOBILENO,
      DOB: DOB,
      SALARY: SALARY,
      WEBSITE: WEBSITE
    };

    NetcoreSDK.profile(payloadata)
      .then(value => {
        console.log("Cancel Pressed");
        Alert.alert(
          "Profile",
          "Profile is updated",
          [
            {
              text: "Cancel",
              onPress: () => console.log("Cancel Pressed"),
              style: "cancel"
            },
            { text: "OK", onPress: () => navigate("HomeScreen", { name: "Home Screen" }) }
          ],
          { cancelable: false }
        );
      })
      .catch(reason => {
        console.log("catch");
        Alert.alert(
          "Error occured",
          reason,
          [
            {
              text: "Cancel",
              onPress: () => console.log("Cancel Pressed"),
              style: "cancel"
            },
            { text: "OK", onPress: () => navigate("HomeScreen", { name: "Home Screen" }) }
          ],
          { cancelable: false }
        );
      });
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

export default Profile;
