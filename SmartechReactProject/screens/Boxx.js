import React, { Component } from 'react';
import { ScrollView, StyleSheet, Text, TextInput, View, Alert } from 'react-native';
import Button from './styling/Button';

const NetcoreSDK = require("smartech-react-native");

const boxxRequestJson = {
  locale: 'EN',
  query: {
    num: 20,
    item_filters: {
      boost_factor: 1
    },
    related_products: [
      'PID1',
      'PID2'
    ],
    context: {
      boxx_location_code: 56
    },
    get_product_properties: true,
    offset: 10,
    related_action_type: 'view',
    exclude: [
      'PID1',
      'PID2'
    ],
    dont_repeat_transaction_types: [
      'ACTION_TYPE1',
      'ACTION_TYPE2'
    ],
    get_product_liked_disliked_status: true,
    get_product_aliases: true,
    KEY2: 2,
    KEY1: 'VALUE1'
  }
};
const boxxRequestString = JSON.stringify(boxxRequestJson);

export class Custom extends Component<Props> {
  constructor(props) {
    super(props);
    this.state = {
      DATA: boxxRequestString
    };
  }

  render() {
    return (
      <View style={styles.container}>
        <ScrollView>
          <Text style={styles.welcome}>Boxx</Text>

          <TextInput
            onChangeText={DATA => this.setState({ DATA })}
            editable={true}
            multiline={true}
            numberOfLines={4}
            defaultValue={boxxRequestString}
            style={styles.inputmulti}
          />
          <Button onPress={this.boxxReco}>Boxx Reco</Button>
          <Button onPress={this.bestSellerReco}>Best Seller Reco</Button>
          <Button onPress={this.newProductsReco}>New Products</Button>
          <Button onPress={this.recentlyViewedReco}>Recently Viewed</Button>
          <Button onPress={this.trendingReco}>Trending</Button>
        </ScrollView>
      </View>
    );
  }

  boxxReco = () => {
    console.log("----------boxxReco----------");

    NetcoreSDK.getBoxxReco(JSON.parse(this.state.DATA))
    .then(response => {
      Alert.alert("value: " + JSON.stringify(response));
      console.log("NCLogger JS Log : " +JSON.stringify(response))
    })
    .catch(error => console.log(error));
  };

  bestSellerReco = () => {
    console.log("----------bestSellerReco----------");

    NetcoreSDK.getBoxxBestSellerReco(JSON.parse(this.state.DATA))
    .then(response => {
      Alert.alert("value: " + JSON.stringify(response));
      console.log("NCLogger JS Log : " +JSON.stringify(response))
    })
    .catch(error => console.log(error));
  };

  newProductsReco = () => {
    console.log("----------newProductsReco----------");

    NetcoreSDK.getBoxxNewProductsReco(JSON.parse(this.state.DATA))
    .then(response => {
      Alert.alert("value: " + JSON.stringify(response));
      console.log("NCLogger JS Log : " +JSON.stringify(response));
    })
    .catch(error => console.log(error));
  };

  recentlyViewedReco = () => {
    console.log("----------recentlyViewedReco----------");

    NetcoreSDK.getBoxxRecentlyViewedReco(JSON.parse(this.state.DATA))
    .then(response => {
      Alert.alert("value: " + JSON.stringify(response));
      console.log("NCLogger JS Log : " +JSON.stringify(response))
    })
    .catch(error => console.log(error));
  };

  trendingReco = () => {
    console.log("----------trendingReco----------");

    NetcoreSDK.getBoxxTrendingReco(JSON.parse(this.state.DATA))
    .then(response => {
      Alert.alert("value: " + JSON.stringify(response));
      console.log("NCLogger JS Log : " +JSON.stringify(response))
    })
    .catch(error => console.log(error));
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
