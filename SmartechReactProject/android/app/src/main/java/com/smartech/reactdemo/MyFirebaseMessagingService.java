package com.smartech.reactdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

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

        // Manually rendering notification
        /*try {
            JSONObject jsonObject = new JSONObject(remoteMessage.getData().toString());
            Log.e("TAG", NetcoreSDK.isNotificationFromNetcore(jsonObject)
                    + "onMessageReceived: " + remoteMessage.getData());
            if (NetcoreSDK.isNotificationFromNetcore(jsonObject)) {
                startNotification(jsonObject);
                NetcoreSDK.deliverNotificationEvent(this, jsonObject, 0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
    private void startNotification(JSONObject jsonObject) {
        Log.i("NextActivity", "startNotification");
        JSONObject data = jsonObject.optJSONObject("data");
        // Sets an ID for the notification
        int mNotificationId = 001;

        // Build Notification , setOngoing keeps the notification always in status bar
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "app_channel")
                        .setSmallIcon(R.drawable.delete_button)
                        .setContentTitle(data.optString("title"))
                        .setContentText(data.optString("message"));

        // Create pending intent, mention the Activity which needs to be
        //triggered when user clicks on notification(StopScript.class in this case)
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("jsonData", jsonObject.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setContentIntent(pendingIntent);
        // Gets an instance of the NotificationManager service
        NotificationManager mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }
}
