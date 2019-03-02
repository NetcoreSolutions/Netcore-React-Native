
import { NativeModules } from 'react-native';

const { SMTSmartechReactNative } = NativeModules;

export default SMTSmartechReactNative;

const Smartech = {
  setIdentity: async identity => {

    try {
      return await SMTSmartechReactNative.setIdentity(identity);
    }
    catch (e) {
      throw e;
    }

  },

  clearIdentity: async () => {
    try {
      return await SMTSmartechReactNative.clearIdentity();
    }
    catch (e) {
      throw e;
    }
  },

  login: async identity => {

    try {
      return await SMTSmartechReactNative.login(identity);
    }
    catch (e) {
      throw e;
    }

  },

  logout: async () => {
    try {
      return await SMTSmartechReactNative.logout();
    }
    catch (e) {
      throw e;
    }
  },

  optOut: async optOutFlag => {
    try {
      return await SMTSmartechReactNative.optOut(optOutFlag);
    }
    catch (e) {
      throw e;
    }
  },

  profile: async profileDetails => {
    try {
      return await SMTSmartechReactNative.profile(profileDetails);
    }
    catch (e) {
      throw e;
    }
  },

  track: (eventName, eventValue) => {
    SMTSmartechReactNative.track(eventName, eventValue);
  },

  setUserLocation: (latitude, longitude) => {
    SMTSmartechReactNative.setUserLocation(latitude, longitude);
  },

  setPushToken: async token => {
    try {
      return await SMTSmartechReactNative.setPushToken(token);
    }
    catch (e) {
      throw e;
    }
  },

  getPushToken: async () => {
    try {
      return await SMTSmartechReactNative.getPushToken();
    }
    catch (e) {
      throw e;
    }

  },

  getGUID: async () => {
    try {
      return await SMTSmartechReactNative.getGUID();
    }
    catch (e) {
      throw e;
    }
  },


  // yet to change in promise
  getNotificationsiOS: async (callback) => {
    SMTSmartechReactNative.getNotifications((error, notifications) => {
      if (error) {
        console.error(error);
      } else {
        callback(notifications);
      }
    });
  },

  getNotifications: async notificationCount => {
    try {
      return await SMTSmartechReactNative.getNotifications(notificationCount);
    }
    catch (e) {
      throw e;
    }
  }
};

module.exports = Smartech;
