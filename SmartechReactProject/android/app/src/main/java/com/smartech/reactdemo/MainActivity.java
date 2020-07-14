package com.smartech.reactdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

import org.json.JSONException;
import org.json.JSONObject;

import in.netcore.smartechfcm.NetcoreSDK;

public class MainActivity extends ReactActivity {
    String TAG = "MainActivity";
    String customData = "No Data";

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "SmartechReactProject";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected Bundle getLaunchOptions() {
                try {
                    Bundle extra = getIntent().getExtras();
                    if (extra != null) {
                        if (extra.containsKey("jsonData")) {
                            Log.e("DeeplinkReceiver", "it has extras" + extra.getString("jsonData"));
                            JSONObject jsonObject = new JSONObject(extra.getString("jsonData"));

                            // String trid = jsonObject.optString("trid");
                            JSONObject data = jsonObject.optJSONObject("data");
                            String trid = data.optString("trid");
                            NetcoreSDK.openNotificationEvent(MainActivity.this, jsonObject);
                        }
                        for (String key : extra.keySet()) {
                            Log.e(TAG, key + " : " + extra.getString(key));
                            if(key.equalsIgnoreCase("customPayload")){
                                customData = extra.getString(key);
                                Toast.makeText(MainActivity.this, customData, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Bundle initialProperties = new Bundle();
                initialProperties.putString("customData", customData);
                return initialProperties;
            }
        };
    }
}
