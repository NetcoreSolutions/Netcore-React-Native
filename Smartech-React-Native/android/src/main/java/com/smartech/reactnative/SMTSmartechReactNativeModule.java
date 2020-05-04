package com.smartech.reactnative;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.smartech.reactnative.helper.ConvertReadable;
import com.smartech.reactnative.helper.JSONConvert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import in.netcore.smartechfcm.NetcoreSDK;
import in.netcore.smartechfcm.Smartech;

public class SMTSmartechReactNativeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private String TAG = SMTSmartechReactNativeModule.class.getSimpleName();

    SMTSmartechReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SMTSmartechReactNative";
    }

    // js exposed methods

    @ReactMethod
    public void setIdentity(String identity, Promise promise) {
        try {
            NetcoreSDK.setIdentity(reactContext, identity);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void clearIdentity(Promise promise) {
        try {
            NetcoreSDK.clearIdentity(reactContext);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void login(String identity, Promise promise) {
        try {
            NetcoreSDK.setIdentity(reactContext, identity);
            NetcoreSDK.login(reactContext);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void logout(Promise promise) {
        try {
            NetcoreSDK.logout(reactContext);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void profile(ReadableMap map, Promise promise) {
        try {
            JSONObject profileData = ConvertReadable.convertMapToJson(map);
            NetcoreSDK.profile(reactContext, profileData);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void track(String eventName, ReadableMap map) {
        try {
            JSONObject payload = new JSONObject();
            payload.put("payload", ConvertReadable.convertMapToJson(map));
            NetcoreSDK.track(reactContext, eventName, payload.toString());
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void optOut(boolean optOutFlag, Promise promise) {
        try {
            NetcoreSDK.optOut(reactContext, optOutFlag);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setUserLocation(Double latitude, Double longitude) {
        try {
            NetcoreSDK.setUserLocation(reactContext, latitude, longitude);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void setPushToken(String token, Promise promise) {
        try {
            NetcoreSDK.setPushToken(reactContext, token);
            promise.resolve(true);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getPushToken(Promise promise) {
        try {
            String token = NetcoreSDK.getPushToken(reactContext);
            promise.resolve(token);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getGUID(Promise promise) {
        try {
            String GUID = NetcoreSDK.getGUID(reactContext);
            promise.resolve(GUID);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getNotifications(int notificationCount, Promise promise) {
        try {
            JSONArray notificationsList = NetcoreSDK.getNotifications(reactContext, notificationCount);
            WritableArray notificationWritableArray = JSONConvert.toWritableMap(notificationsList);
            promise.resolve(notificationWritableArray);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getUnreadNotificationsCount(Promise promise) {
        try {
            int notificationsCount = NetcoreSDK.getUnreadNotificationsCount(reactContext);
            promise.resolve(notificationsCount);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    // New Methods

    @ReactMethod
    public void setUserIdentity(String identity) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).setUserIdentity(identity);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void clearUserIdentity() {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).clearUserIdentity();
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void getUserIdentity(Promise promise) {
        try {
            String identity = Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getUserIdentity();
            promise.resolve(identity);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void logoutAndClearUserIdentity(Boolean clearUserIdentity) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).logoutAndClearUserIdentity(clearUserIdentity);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void updateUserProfile(ReadableMap map) {
        try {
            HashMap<String, Object> profileData = ConvertReadable.convertReadableMapToHashMap(map);
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).updateUserProfile(profileData);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void trackEvent(String eventName, ReadableMap map) {
        try {
            HashMap<String, Object> eventData = ConvertReadable.convertReadableMapToHashMap(map);
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).trackEvent(eventName, eventData);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void getOptOutStatus(Promise promise) {
        try {
            boolean status = Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getOptOutStatus();
            promise.resolve(status);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setDevicePushToken(String token) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).setDevicePushToken(token);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void getDevicePushToken(Promise promise) {
        try {
            String token = Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getDevicePushToken();
            promise.resolve(token);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getDeviceUniqueId(Promise promise) {
        try {
            String GUID = Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getDeviceUniqueId();
            promise.resolve(GUID);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void handlePushNotification(String notificationData, Promise promise) {
        try {
            boolean pushFromSmartech = Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).handlePushNotification(notificationData);
            promise.resolve(pushFromSmartech);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void deliverNotificationEvent(JSONObject json, int isAmplified) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).deliverNotificationEvent(reactContext, json, isAmplified);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void openNotificationEvent(String trID, String deeplink, String payload) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).openNotificationEvent(reactContext, trID, deeplink, payload);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

}