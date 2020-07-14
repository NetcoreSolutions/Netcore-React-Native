package com.smartech.reactnative;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.smartech.reactnative.helper.CommonUtils;
import com.smartech.reactnative.helper.ConvertReadable;
import com.smartech.reactnative.helper.JSONConvert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;

import in.netcore.smartechfcm.NetcoreSDK;
import in.netcore.smartechfcm.Smartech;
import in.netcore.smartechfcm.boxx.model.SMTBoxxRequest;
import in.netcore.smartechfcm.boxx.model.SMTBoxxRequestQuery;
import in.netcore.smartechfcm.boxx.model.SMTBoxxResponse;
import in.netcore.smartechfcm.boxx.model.SMTBoxxResponseListener;

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
    public void openNotificationEvent(JSONObject metaData, String trID, String deeplink, String payload) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).openNotificationEvent(reactContext, metaData, trID, deeplink, payload);
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }
    }

    @ReactMethod
    public void getBoxxReco(ReadableMap boxxRequestJsonString, final Promise promise) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getBoxxReco(reactContext, stringToSMTBoxxRequest(boxxRequestJsonString), new SMTBoxxResponseListener() {
                @Override
                public void onResponse(SMTBoxxResponse smtBoxxResponse) {
                    handleBoxxResponse(smtBoxxResponse, promise);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getBoxxBestSellerReco(ReadableMap boxxRequestJsonString, final Promise promise) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getBoxxBestSellerReco(reactContext, stringToSMTBoxxRequest(boxxRequestJsonString), new SMTBoxxResponseListener() {
                @Override
                public void onResponse(SMTBoxxResponse smtBoxxResponse) {
                    handleBoxxResponse(smtBoxxResponse, promise);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getBoxxNewProductsReco(ReadableMap boxxRequestJsonString, final Promise promise) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getBoxxNewProductsReco(reactContext, stringToSMTBoxxRequest(boxxRequestJsonString), new SMTBoxxResponseListener() {
                @Override
                public void onResponse(SMTBoxxResponse smtBoxxResponse) {
                    handleBoxxResponse(smtBoxxResponse, promise);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getBoxxRecentlyViewedReco(ReadableMap boxxRequestJsonString, final Promise promise) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getBoxxRecentlyViewedReco(reactContext, stringToSMTBoxxRequest(boxxRequestJsonString), new SMTBoxxResponseListener() {
                @Override
                public void onResponse(SMTBoxxResponse smtBoxxResponse) {
                    handleBoxxResponse(smtBoxxResponse, promise);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void getBoxxTrendingReco(ReadableMap boxxRequestJsonString, final Promise promise) {
        try {
            Smartech.Companion.getInstance(new WeakReference<>(reactContext.getApplicationContext())).getBoxxTrendingReco(reactContext, stringToSMTBoxxRequest(boxxRequestJsonString), new SMTBoxxResponseListener() {
                @Override
                public void onResponse(SMTBoxxResponse smtBoxxResponse) {
                    handleBoxxResponse(smtBoxxResponse, promise);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
            promise.reject(e);
        }
    }

    private SMTBoxxRequest stringToSMTBoxxRequest(ReadableMap boxxRequestJsonString) {
        SMTBoxxRequest smtBoxxRequest = null;

        try {
            JSONObject boxxRequestJson = ConvertReadable.convertMapToJson(boxxRequestJsonString);
            smtBoxxRequest = new SMTBoxxRequest();

            smtBoxxRequest.setLocale(boxxRequestJson.optString("locale", null));

            JSONObject boxxRequestQuery = boxxRequestJson.optJSONObject("query");
            if (boxxRequestQuery != null) {
                SMTBoxxRequestQuery.Builder queryBuilder = new SMTBoxxRequestQuery.Builder();

                if (boxxRequestQuery.has("item_filters")) {
                    queryBuilder.setItemFilters(boxxRequestQuery.getJSONObject("item_filters"));
                    boxxRequestQuery.remove("item_filters");
                }

                if (boxxRequestQuery.has("related_products")) {
                    queryBuilder.setRelatedProducts(CommonUtils.jsonArrayToStringArray(boxxRequestQuery.getJSONArray("related_products")));
                    boxxRequestQuery.remove("related_products");
                }

                if (boxxRequestQuery.has("context")) {
                    queryBuilder.setContext(boxxRequestQuery.getJSONObject("context"));
                    boxxRequestQuery.remove("context");
                }

                if (boxxRequestQuery.has("get_product_properties")) {
                    queryBuilder.setGetProductProperties(boxxRequestQuery.getBoolean("get_product_properties"));
                    boxxRequestQuery.remove("get_product_properties");
                }

                if (boxxRequestQuery.has("num")) {
                    queryBuilder.setNum(boxxRequestQuery.getInt("num"));
                    boxxRequestQuery.remove("num");
                }

                if (boxxRequestQuery.has("offset")) {
                    queryBuilder.setOffset(boxxRequestQuery.getInt("offset"));
                    boxxRequestQuery.remove("offset");
                }

                if (boxxRequestQuery.has("related_action_type")) {
                    queryBuilder.setRelatedActionType("related_action_type");
                    boxxRequestQuery.remove("related_action_type");
                }

                if (boxxRequestQuery.has("exclude")) {
                    queryBuilder.setExclude(CommonUtils.jsonArrayToStringArray(boxxRequestQuery.getJSONArray("exclude")));
                    boxxRequestQuery.remove("exclude");
                }

                if (boxxRequestQuery.has("dont_repeat_transaction_types")) {
                    queryBuilder.setDontRepeatTransactionTypes(CommonUtils.jsonArrayToStringArray(boxxRequestQuery.getJSONArray("dont_repeat_transaction_types")));
                    boxxRequestQuery.remove("dont_repeat_transaction_types");
                }

                if (boxxRequestQuery.has("get_product_liked_disliked_status")) {
                    queryBuilder.setGetProductLikedDislikedStatus(boxxRequestQuery.getBoolean("get_product_liked_disliked_status"));
                    boxxRequestQuery.remove("get_product_liked_disliked_status");
                }

                if (boxxRequestQuery.has("get_product_aliases")) {
                    queryBuilder.setGetProductAliases(boxxRequestQuery.getBoolean("get_product_aliases"));
                    boxxRequestQuery.remove("get_product_aliases");
                }

                // Adding remaining parameters as extras.
                Iterator<String> extraKeys = boxxRequestQuery.keys();
                HashMap<String, Object> extras = new HashMap<>();
                while (extraKeys.hasNext()) {
                    String key = extraKeys.next();
                    Object value = boxxRequestQuery.get(key);
                    extras.put(key, value);
                }
                queryBuilder.setExtras(extras);

                smtBoxxRequest.setQuery(queryBuilder.build());
            }
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }

        return smtBoxxRequest;
    }

    private void handleBoxxResponse(SMTBoxxResponse smtBoxxResponse, Promise promise) {
        try {
            JSONObject smtBoxxResponseJson = new JSONObject();
            smtBoxxResponseJson.put("response_code", smtBoxxResponse.getResponseCode());
            smtBoxxResponseJson.put("response_message", smtBoxxResponse.getResponseMessage());
            smtBoxxResponseJson.put("response_data", smtBoxxResponse.getResponseData());
            promise.resolve(JSONConvert.toWritableMap(smtBoxxResponseJson));
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e);
        }
    }
}