import React, { Component } from 'react';
import { ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import Button from './styling/Button';

const NetcoreSDK = require("smartech-react-native");

const mypayloadata = {
    name: 'Galaxy',
    description: '20gram bars',
    id: '1'
};
const json_payload = JSON.stringify(mypayloadata);

export class Custom extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      NAME: '',
      DATA: json_payload,
      DATA1: ''
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <ScrollView>
          <Text style={styles.welcome}>Custom Payload</Text>
          <TextInput
            placeholder="ENTER NAME"
            onChangeText={NAME => this.setState({ NAME })}
            style={styles.input}
          />

          <TextInput
            onChangeText={DATA => this.setState({ DATA })}
            editable={true}
            multiline={true}
            numberOfLines={4}
            value={json_payload.toString()}
            style={styles.inputmulti}
          />
          <TextInput
            onChangeText={DATA1 => this.setState({ DATA1 })}
            editable={true}
            multiline={true}
            numberOfLines={4}
            style={styles.inputmulti}
          />
          <Button onPress={this.save}>Save</Button>
        </ScrollView>
      </View>
    );
  }

  save = () => {
    const NAME = this.state.NAME;
    const DATA = this.state.DATA;
    const DATA1 = this.state.DATA1;

    console.log(NAME, DATA, DATA1);

    NetcoreSDK.track(NAME.trim(), mypayloadata);
    NetcoreSDK.track(NAME.trim(), JSON.parse(DATA1));
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
  },
  inputmulti: {
    margin: 15,
    borderColor: '#337ab7',
    borderWidth: 1,
    width: 250
  }
});

export default Custom;
