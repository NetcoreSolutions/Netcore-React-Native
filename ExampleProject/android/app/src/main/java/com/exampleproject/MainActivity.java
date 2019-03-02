package com.exampleproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import in.netcore.smartechfcm.NetcoreSDK;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "ExampleProject";
    }

    String TAG = "MainActivity";
    String customData = "No Data";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null)
                for (String key : bundle.keySet()) {
                    Log.e(TAG, key+" : "+bundle.get("customPayload").toString());
                    customData = bundle.get(key).toString();
                    Toast.makeText(MainActivity.this,customData,Toast.LENGTH_LONG).show();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected Bundle getLaunchOptions() {
                Bundle initialProperties = new Bundle();
                initialProperties.putString("customData", customData);
                return initialProperties;
            }
        };
    }
}
