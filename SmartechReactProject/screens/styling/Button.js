import React from 'react';
import { Text, TouchableOpacity } from 'react-native';

const Button = ({ onPress, children }) => {
  const { buttonStyle, textStyle } = styles;
  return (
    <TouchableOpacity onPress={onPress} style={buttonStyle}>
      <Text style={textStyle}>
        {children}
      </Text>
    </TouchableOpacity>
  );
};

export default Button;

const styles = {
  textStyle: {
    alignSelf: 'center',
    color: '#ffffff',
    fontSize: 16,
    fontWeight: '600',
    paddingTop: 8,
    paddingBottom: 8
  },
  buttonStyle: {
    backgroundColor: '#337ab7',
    borderWidth: 1,
    borderColor: '#337ab7',
    paddingTop: 6,
    paddingBottom: 6,
    paddingRight: 25,
    paddingLeft: 25,
    marginTop: 10,
    width: 250
  }
}; 
