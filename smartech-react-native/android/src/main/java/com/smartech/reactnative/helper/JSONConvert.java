package com.smartech.reactnative.helper;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONConvert {

    public static WritableMap toWritableMap(JSONObject jsonObject) throws JSONException {
        WritableMap writableMap = Arguments.createMap();
        Iterator iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object value = jsonObject.get(key);
            if (value instanceof Float || value instanceof Double) {
                writableMap.putDouble(key, jsonObject.getDouble(key));
            } else if (value instanceof Number) {
                writableMap.putInt(key, jsonObject.getInt(key));
            } else if (value instanceof String) {
                writableMap.putString(key, jsonObject.getString(key));
            } else if (value instanceof JSONObject) {
                writableMap.putMap(key, toWritableMap(jsonObject.getJSONObject(key)));
            } else if (value instanceof JSONArray) {
                writableMap.putArray(key, toWritableMap(jsonObject.getJSONArray(key)));
            } else if (value == JSONObject.NULL) {
                writableMap.putNull(key);
            }
        }

        return writableMap;
    }

    public static WritableArray toWritableMap(JSONArray jsonArray) throws JSONException {
        WritableArray writableArray = Arguments.createArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof Float || value instanceof Double) {
                writableArray.pushDouble(jsonArray.getDouble(i));
            } else if (value instanceof Number) {
                writableArray.pushInt(jsonArray.getInt(i));
            } else if (value instanceof String) {
                writableArray.pushString(jsonArray.getString(i));
            } else if (value instanceof JSONObject) {
                writableArray.pushMap(toWritableMap(jsonArray.getJSONObject(i)));
            } else if (value instanceof JSONArray) {
                writableArray.pushArray(toWritableMap(jsonArray.getJSONArray(i)));
            } else if (value == JSONObject.NULL) {
                writableArray.pushNull();
            }
        }
        return writableArray;
    }
}
