package com.exampleproject;

import android.app.Application;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.facebook.react.ReactApplication;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.smartech.reactnative.SMTSmartechReactNativePackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

import in.netcore.smartechfcm.NetcoreSDK;
import io.fabric.sdk.android.Fabric;

public class MainApplication extends Application implements ReactApplication {

  private static final String appID= "<GET_APP_ID_FROM_SMARTECH_PANEL>";
  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new RNGestureHandlerPackage(),
            new SMTSmartechReactNativePackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    NetcoreSDK.register(this,appID);
    NetcoreSDK.setPushIcon(this,R.drawable.ic_notification);
    Fabric.with(this, new Crashlytics());

  }
}
