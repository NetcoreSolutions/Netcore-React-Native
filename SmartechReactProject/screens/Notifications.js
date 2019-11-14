import React, { Component } from 'react';
import { StyleSheet, Platform, View, ActivityIndicator, FlatList, Text, Image, Alert } from 'react-native';
import Button from './styling/Button';

const NetcoreSDK = require("smartech-react-native");

export class Notifications extends Component<Props> {
  constructor(props) {
    super(props);
      this.state = {
      isLoading: true
    }
  }
   
  componentDidMount(){
    this.callGetNotification();
 }
  callGetNotification = () => {
      NetcoreSDK.getNotifications(10)
      .then(value => {
	
        let jsonObject = JSON.stringify(value);
	console.log("Notifications data"+jsonObject);
	this.setState({
             isLoading: false,
             dataSource: value
           }, function() {
             
           }); 
      })
      .catch(reason => console.log(reason));
  };
FlatListItemSeparator = () => {
   return (
     <View
       style={{
         height: .5,
         width: "100%",
         backgroundColor: "#000",
       }}
     />
   );
 }
 _renderItem = ({item}) => (
    <MyListItem
      id={item.id}
      onPressItem={this._onPressItem}
      selected={!!this.state.selected.get(item.id)}
      title={item.title}
    />
  );
  render() {
   if (this.state.isLoading) {
     return (
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
         <ActivityIndicator size="large" />
       </View>
     );
   }
   return (
     <View style={styles.MainContainer}>
     <FlatList
	data={this.state.dataSource}
	renderItem={({item}) =>
	 <View style={styles.container}>
	    <Text style={styles.textView} >Title: {JSON.parse(item.message).data.title}</Text>
	    <Text style={styles.textView} >Message: {JSON.parse(item.message).data.message}</Text>
	    <Text style={styles.textView} >Status: {item.status}</Text>
	    <Text style={styles.textView} >-----------------</Text>
	 </View>
	}
      />
     </View>
   );
 }
}
const styles = StyleSheet.create({
 
MainContainer :{
    justifyContent: 'center',
    flex:1,
    margin: 5,
    marginTop: (Platform.OS === 'ios') ? 20 : 0,
},
 
imageView: {
    width: '50%',
    height: 100 ,
    margin: 7,
    borderRadius : 7
},
 
textView: {
    width:'50%', 
    textAlignVertical:'center',
    padding:10,
    color: '#000'
}
});

export default Notifications;

