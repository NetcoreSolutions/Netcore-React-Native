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
    },

    getUnreadNotificationsCount: async () => {
        try {
            return await SMTSmartechReactNative.getUnreadNotificationsCount();
        }
        catch (e) {
            throw e;
        }
    },

    setUserIdentity: (identity) => {
        SMTSmartechReactNative.setUserIdentity(identity);
    },
    clearUserIdentity: () => {
        SMTSmartechReactNative.clearUserIdentity();
    },
    getUserIdentity: async () => {
        try {
            return await SMTSmartechReactNative.getUserIdentity();
        }
        catch (e) {
            throw e;
        }
    },
    logoutAndClearUserIdentity: (clearUserIdentity) => {
        SMTSmartechReactNative.logoutAndClearUserIdentity(clearUserIdentity);
    },
    updateUserProfile: (profileDetails) => {
        SMTSmartechReactNative.updateUserProfile(profileDetails);
    },
    trackEvent: (eventName, eventValue) => {
        SMTSmartechReactNative.trackEvent(eventName, eventValue);
    },
    setDevicePushToken: (token) => {
        SMTSmartechReactNative.setDevicePushToken(token);
    },
    getDevicePushToken: async () => {
        try {
            return await SMTSmartechReactNative.getDevicePushToken();
        }
        catch (e) {
            throw e;
        }
    },
    getOptOutStatus: async () => {
        try {
            return await SMTSmartechReactNative.getOptOutStatus();
        }
        catch (e) {
            throw e;
        }
    },

    getDeviceUniqueId: async () => {
        try {
            return await SMTSmartechReactNative.getDeviceUniqueId();
        }
        catch (e) {
            throw e;
        }
    },

    handlePushNotification: async notificationData => {
        try {
            return await SMTSmartechReactNative.handlePushNotification(notificationData);
        }
        catch (e) {
            throw e;
        }
    },
    deliverNotificationEvent: (json, isAmplified) => {
        SMTSmartechReactNative.deliverNotificationEvent(json, isAmplified);
    },
    openNotificationEvent: (metaData, trID, deeplink, payload) => {
        SMTSmartechReactNative.openNotificationEvent(metaData, trID, deeplink, payload);
    },
    getBoxxReco: async (smtBoxxRequestJson) => {
        try {
            return await SMTSmartechReactNative.getBoxxReco(smtBoxxRequestJson);
        }
        catch (e) {
            throw e;
        }
    },
    getBoxxBestSellerReco: async (smtBoxxRequestJson) => {
        try {
            return await SMTSmartechReactNative.getBoxxBestSellerReco(smtBoxxRequestJson);
        }
        catch (e) {
            throw e;
        }
    },
    getBoxxNewProductsReco: async (smtBoxxRequestJson) => {
        try {
            return await SMTSmartechReactNative.getBoxxNewProductsReco(smtBoxxRequestJson);
        }
        catch (e) {
            throw e;
        }
    },
    getBoxxRecentlyViewedReco: async (smtBoxxRequestJson) => {
        try {
            return await SMTSmartechReactNative.getBoxxRecentlyViewedReco(smtBoxxRequestJson);
        }
        catch (e) {
            throw e;
        }
    },
    getBoxxTrendingReco: async (smtBoxxRequestJson) => {
        try {
            return await SMTSmartechReactNative.getBoxxTrendingReco(smtBoxxRequestJson);
        }
        catch (e) {
            throw e;
        }
    }
};

module.exports = Smartech;
