package com.smartech.reactdemo;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.netcore.smartechfcm.NetcoreSDK;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        NetcoreSDK.setPushToken(this, token);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        boolean pushFromSmartech = NetcoreSDK.handleNotification(getApplicationContext(), remoteMessage.getData());
        if(!pushFromSmartech){
            //Handle the notification received from other sources
        }
    }
}
