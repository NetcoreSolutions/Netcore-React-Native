# Integrating the Smartech SDK

After [install](./install.md), you will need to integrate the Smartech SDK into your Android apps.

## Android

### To register device for push notifications

To register the device for receiving push notifications from Smartech panel​, add given snippet inside the **onCreate method of the ``MainApplication`` class in android native​​.**
```java
NetcoreSDK.register(this, <app_id>);
```
### To use custom push notification icon

SDK uses launcher icon for push notifications by default and in order to change it, use a custom icon by adding given snippet inside  **onCreate method of the ``MainApplication`` class in android native​​.**

```java
NetcoreSDK.setPushIcon(context, <path_to_drawable_icon>);

E.g.
NetcoreSDK.setPushIcon(context, R.drawable.ic_push_icon);
```
**Note:** The notification icon being used should strictly be in .png format as per [Google’s guidelines](https://developer.android.com/guide/practices/ui_guidelines/icon_design_status_bar)​. Preferable size for the push notification icons are mentioned below.

```java 
drawable-mdpi        :  24 x 24
drawable-hdpi        :  36 x 36
drawable-xhdpi       :  48 x 48
drawable-xxhdpi      :  72 x 72
drawable-xxxhdpi     :  96 x 96
```

### To implement push notifications in existing FCM class of Android

To use push notifications from Smartech panel along with existing set up of the FCM class, add given snippet ​ **inside the ``FCM receiver class​`` in native​.**

```java
boolean pushFromSmartech = NetcoreSDK.handleNotification(context, remoteMessage.getData());
```
**Note​​:** The method returns a boolean value.
- Returns **true​**, if the push notification is received from the Smartech panel. It will also render the push notification without any extra efforts further.

- Return ​**false​**, if the push notification is not received from the Smartech panel. In this case, handle the push notification at your end as per the requirement.


### To set existing FCM token

To set existing FCM token of the application to the SDK, add given snippet just before **‘register’ method in the ``MainApplication`` class of the app.**

```java
NetcoreSDK.setPushToken(context, <token_string>);

e.g.
NetcoreSDK.setPushToken(context, <token_string>);
NetcoreSDK.register(this, <app_id>);
```

#### To implement deeplink in the application
To implement deeplink in the application, add given snippet **inside AndroidManifest.xml file with in the Activity Tag**.
```xml
<intent-filter>
	<action android:name = "android.intent.action.VIEW"/>
	<category android:name = "android.intent.category.DEFAULT"/>
	<category android:name = "android.intent.category.BROWSABLE"/>
	<data android:scheme = "<scheme>" android:host= "<host>"/>
</intent-filter>
    
e.g.
<intent-filter>
	<action android:name= "android.intent.action.VIEW"/>
	<category android:name= "android.intent.category.DEFAULT"/>
	<category android:name= "android.intent.category.BROWSABLE"/>
	<data android:scheme = "smartech" android:host= "products"/>
</intent-filter>
```
