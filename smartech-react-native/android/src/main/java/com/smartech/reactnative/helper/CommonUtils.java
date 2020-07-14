package com.smartech.reactnative.helper;

import android.util.Log;

import org.json.JSONArray;

public class CommonUtils {
    public static final String TAG = CommonUtils.class.getSimpleName();

    public static String[] jsonArrayToStringArray(JSONArray jsonArray) {
        String[] stringArray = new String[jsonArray.length()];

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                stringArray[i] = jsonArray.getString(i);
            }

            return stringArray;
        } catch (Exception e) {
            Log.e(TAG, "Netcore Error: " + e.getMessage());
        }

        return null;
    }
}