
package com.smartech.reactnative;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.smartech.reactnative.helper.ConvertReadable;
import com.smartech.reactnative.helper.JSONConvert;

import org.json.JSONArray;
import org.json.JSONObject;

import in.netcore.smartechfcm.NetcoreSDK;

public class SMTSmartechReactNativeModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private String TAG = SMTSmartechReactNativeModule.class.getSimpleName();

  public SMTSmartechReactNativeModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "SMTSmartechReactNative";
  }

  @ReactMethod
  public void setIdentity(String identity, Promise promise) {
    try {
      NetcoreSDK.setIdentity(reactContext, identity);
      promise.resolve(true);

    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void clearIdentity(Promise promise) {
    try {
      NetcoreSDK.clearIdentity(reactContext);
      promise.resolve(true);
    } catch (Exception e) {
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
      promise.reject(e);
    }
  }

  @ReactMethod
  public void logout(Promise promise) {
    try {
      NetcoreSDK.logout(reactContext);
      promise.resolve(true);
    } catch (Exception e) {
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
      promise.reject(e);
    }
  }

  @ReactMethod
  public void track(String eventName, ReadableMap map) {
    try {
      JSONObject payload = new JSONObject();
      payload.put("payload",ConvertReadable.convertMapToJson(map));
      NetcoreSDK.track(reactContext, eventName, payload.toString());
    } catch (Exception e) {
      Log.w(TAG, e.getMessage());
    }
  }
  
  @ReactMethod
  public void optOut(boolean optOutFlag, Promise promise) {
    try {
      NetcoreSDK.optOut(reactContext, optOutFlag);
      promise.resolve(true);
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void setUserLocation(Double latitude, Double longitude) {
    try {
      NetcoreSDK.setUserLocation(reactContext, latitude, longitude);
    } catch (Exception e) {
      Log.w(TAG, e.getMessage());
    }
  }

  @ReactMethod
  public void setPushToken(String token, Promise promise) {
    try {
      NetcoreSDK.setPushToken(reactContext, token);
      promise.resolve(true);
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void getPushToken(Promise promise) {
    try {
      String token = NetcoreSDK.getPushToken(reactContext);
      promise.resolve(token);
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void getGUID(Promise promise) {
    try {
      String GUID = NetcoreSDK.getGUID(reactContext);
      promise.resolve(GUID);
    } catch (Exception e) {
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
      promise.reject(e);
    }
  }
  @ReactMethod
  public void getUnreadNotificationsCount(Promise promise) {
    try {
      int notificationsCount = NetcoreSDK.getUnreadNotificationsCount(reactContext);
      promise.resolve(notificationsCount);
    } catch (Exception e) {
      promise.reject(e);
    }
  }
  @ReactMethod
  public void handleNotification(ReadableMap notificationData, Promise Promise) {
    try {
      boolean pushFromSmartech = NetcoreSDK.handleNotification(reactContext, ConvertReadable.convertReadableMapToMap(notificationData));
      Promise.resolve(pushFromSmartech);
    } catch (Exception e) {
      Promise.reject(e);
    }
  }
  @ReactMethod
  public void markNotificationAsRead(String trID, String deeplink, Promise promise) {
    try {
      NetcoreSDK.markNotificationAsRead(reactContext, trID, deeplink);
      promise.resolve(true);
    } catch (Exception e) {
      promise.reject(e);
    }
  }
}