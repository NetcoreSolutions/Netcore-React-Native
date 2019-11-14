package com.smartech.reactdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;

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
                Bundle initialProperties = new Bundle();
                initialProperties.putString("customData", customData);
                return initialProperties;
            }
        };
    }
}
