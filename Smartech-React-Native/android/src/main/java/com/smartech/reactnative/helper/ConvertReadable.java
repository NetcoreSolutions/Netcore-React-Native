package com.smartech.reactnative.helper;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConvertReadable {

    public static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject object = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    object.put(key, JSONObject.NULL);
                    break;
                case Boolean:
                    object.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    object.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    object.put(key, readableMap.getString(key));
                    break;
                case Map:
                    ReadableMap map = readableMap.getMap(key);
                    if (map != null) {
                        object.put(key, convertMapToJson(map));
                    }
                    break;
                case Array:
                    ReadableArray readableArray = readableMap.getArray(key);
                    if (readableArray != null) {
                        object.put(key, convertArrayToJson(readableArray));
                    }
                    break;
            }
        }
        return object;
    }

    private static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    ReadableMap map = readableArray.getMap(i);
                    if (map != null) {
                        array.put(convertMapToJson(map));
                    }
                    break;
                case Array:
                    ReadableArray arr = readableArray.getArray(i);
                    if (arr != null) {
                        array.put(convertArrayToJson(arr));
                    }
                    break;
            }
        }
        return array;
    }

    public static HashMap<String, Object> convertReadableMapToHashMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        HashMap<String, Object> deconstructedMap = new HashMap<>();
        try {
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                ReadableType type = readableMap.getType(key);
                switch (type) {
                    case Null:
                        deconstructedMap.put(key, null);
                        break;
                    case Boolean:
                        deconstructedMap.put(key, readableMap.getBoolean(key));
                        break;
                    case Number:
                        deconstructedMap.put(key, readableMap.getDouble(key));
                        break;
                    case String:
                        deconstructedMap.put(key, readableMap.getString(key));
                        break;
                    case Map:
                        ReadableMap map = readableMap.getMap(key);
                        if (map != null) {
                            deconstructedMap.put(key, convertReadableMapToHashMap(map));
                        }
                        break;
                    case Array:
                        ReadableArray readableArray = readableMap.getArray(key);
                        if (readableArray != null) {
                            deconstructedMap.put(key, convertReadableArrayToList(readableArray));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return deconstructedMap;
    }

    private static List<Object> convertReadableArrayToList(ReadableArray readableArray) {
        List<Object> deconstructedList = new ArrayList<>(readableArray.size());
        try {
            for (int i = 0; i < readableArray.size(); i++) {
                ReadableType indexType = readableArray.getType(i);
                switch (indexType) {
                    case Null:
                        deconstructedList.add(i, null);
                        break;
                    case Boolean:
                        deconstructedList.add(i, readableArray.getBoolean(i));
                        break;
                    case Number:
                        deconstructedList.add(i, readableArray.getDouble(i));
                        break;
                    case String:
                        deconstructedList.add(i, readableArray.getString(i));
                        break;
                    case Map:
                        ReadableMap map = readableArray.getMap(i);
                        if (map != null) {
                            deconstructedList.add(i, convertReadableMapToHashMap(map));
                        }
                        break;
                    case Array:
                        ReadableArray array = readableArray.getArray(i);
                        if (array != null) {
                            deconstructedList.add(i, convertReadableArrayToList(array));
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Could not convert object at index " + i + ".");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return deconstructedList;
    }
}