

# Integrating the Smartech SDK

After [install](./install.md), you will need to integrate the Smartech SDK into your Android and iOS apps.

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

### To implement deeplink in the application
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

### To fetch Advertising Id
To fetch Advertising Id using Smartech SDK of the device, add given snippet **inside AndroidManifest.xml file with in the Activity Tag**.

```xml

<meta-data
android:name="SMT_USE_AD_ID"
android:value="<value>"/>

e.g.
<meta-data
android:name="SMT_USE_AD_ID"
android:value="1"/>
```

**Note​​:** The method accepts either **‘0’** or **‘1’** as value.  
- If an app wants Smartech SDK to fetch Advertising Id of the device, use **‘1’** as value.  
- If an app does not want Smartech SDK to fetch Advertising Id of the device, use **‘0’** as value.

### To fetch custom payload from push notifications
To fetch custom payload data from the push notifications, add given snippet in the **activity** of the application as per the requirement.
```java
Bundle bundle = getIntent().getExtras();
JSONObject jsonObject = new JSONObject(bundle.getString("customPayload"));
```

## Additional Resources for Android

-  [Smartech Android SDK Integration guide](https://docs.netcoresmartech.com/docs/android-sdk)


## iOS

1. Import following file in App Delegate File

```objective-c
#import <NetCorePush/NetCorePush.h>
```

2. Add NetCore Application AppID in support in Finish Launching Methods (AppDelegate file)

```objective-c
[[NetCoreSharedManager  sharedInstance] setUpAppGroup:@""];
[[NetCoreSharedManager  sharedInstance] handleApplicationLaunchEvent:launchOptions forApplicationId:@""];
//set up push delegate
[NetCorePushTaskManager  sharedInstance].delegate = (id)self;
if (@available(iOS 10.0, *)) {
    [UNUserNotificationCenter currentNotificationCenter].delegate = self;
} else {
    // Fallback on earlier versions
}
```

3. Register Device With NetCore SDK (AppDelegate file)
````objective-c
-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
    [[NetCoreInstallation  sharedInstance] netCorePushRegisteration:[[NetCoreSharedManager  sharedInstance]getIdentity] withDeviceToken:deviceToken Block:^(NSInteger statusCode) {
    }];
}

````

### For Normal Push Notifications

````objective-c
//Handle Remote/Local Notification Delegate Events (AppDelegate file)

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {

    [[NetCorePushTaskManager  sharedInstance] didReceiveRemoteNotification:userInfo];
}
// Adding UNUserNotificationCenter Delegate Method (v1.1.2 onwards)
- (void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions))completionHandler {
    completionHandler(UNNotificationPresentationOptionAlert);
}
- (void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void (^)(void))completionHandler {

    [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:response.notification.request.content.userInfo];

    completionHandler();
}

````

### To Handle URL Link

```objective-c

- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,**id**> *)options {
    return  true;
}

```

## To Handle Deep Link and Custom Payload (v1.1.2 onwards)
```objective-c
- (void)handleSmartechDeeplink:(SMTDeeplink *)options {
    if (options.deepLinkType == SMTDeeplinkTypeUrl) {
        // When Deeplink is WebURL
    }

    else if (options.deepLinkType == SMTDeeplinkTypeUniversalLink) {
        // When Deeplink is Universal-link
    }

    else if (options.deepLinkType == SMTDeeplinkTypeDeeplink) {
        // When Deeplink is URLSchemes link.
    }

    else if (options.deepLinkType == SMTDeeplinkTypeDeeplink) {
        // When Deeplink is Empty.
    }

    else if (options.deepLinkType == SMTDeeplinkTypeApp) {
    }

    // options.customPayload is send from notification.

    // options.userInfo is notification Pyaload.
}

```
## To Handle Deep Link(Till v1.1.1)
```objective-c

//For Handling deep link
- (void)handleNotificationOpenAction:(NSDictionary *)userInfo DeepLinkType:(NSString *)strType
{
}

```
## To Handle Custom Payload(Till v1.1.1)

```objective-c

//For Handle custom payload
- (void)handleNotificationCustomPayload:(NSDictionary *)payload
{

}

```
## To Setting Universal Link

You need to pass the universal link value to the SDK that you have added in your Capabilities -> Associated Domains section Eg. applinks:https://www.netcoresmartech.com then enter only domain name as https://www.netcoresmartech.com, you can pass multiple domain names.

```objective-c
[[NetCoreSharedManager sharedInstance] setAssociateDomain:@[@"type-your-universal-link"]];
```

### For Rich Push Notifications

#### Configuration Changes

```objective-c

1) Add “Notification Service Extension” to your app. File->New->Target- >Notification Service Extension.

2) Click Next and when asked to “Activate”, Click Activate.

3) Add “App Groups” to your apps Capabilities(Add one group with name "<group.com.CompanyName.ProductName>").

4) Enable App groups in Service Extension too and select group with name "<group.com.CompanyName.ProductName>".

5) If App group is not activated on the provisioning profile you are using, then

6) Enable App groups in your provisioning profile from your Apple Developer’s account and replace the profile with the new one. Or,

7) In your apps’s, Target->General-> Signing, Select “Automatically manage signing” and enable App groups by going to Target->Capabilities->App group. This will automatically add app groups capability to you provisioning profile.

```
#### Implementation Changes



Remove all the code written in “NotificationService” implementation class.



1) Import NetCore Framework into Extension

```objective-c

#import <NetCorePush/NetCorePush.h>

```

2) Handle Notification Request

```objective-c

- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request withContentHandler:(void (^)(UNNotificationContent * _Nonnull))contentHandler {

    [[NetCoreNotificationService  sharedInstance] setUpAppGroup:@"group.com.netcore.SmartechObjc"];
    [[NetCoreNotificationService  sharedInstance] didReceiveNotificationRequest:request withContentHandler:^(UNNotificationContent *contentToDeliver) {contentHandler(contentToDeliver);
    }];
}

```

3) Handle Notification Service Time Expire

```objective-c

- (void)serviceExtensionTimeWillExpire {

    [[NetCoreNotificationService  sharedInstance] serviceExtensionTimeWillExpire];
    }
    
```



### For Carousel Push Notifications

#### Configuration Changes

```objective-c
1) Add “Notification Content Extension” to your app. File->New->Target->Notification Content Extension.

2) Click Next and when asked to “Activate”, Click Activate.

3) Add “App Groups” to your apps Capabilities(Add one group with name "<group.com.CompanyName.ProductName>").

4) Enable “App Groups” in Content Extension too and select group with name "<group.com.CompanyName.ProductName>".
```

## Additional Resources for iOS

-  [Smartech iOS SDK Integration guide](https://docs.netcoresmartech.com/docs/ios-sdk-integration)


#### Implementation Changes

```objective-c
1) Replace “MainInterface.storyboard” of Content Extension with the one provided in "Rich Files".

2) In “Info.plist” file of Content Extension, replace “UNNotificationExtensionCategory” value with “SmartechPushCategory”.

3) In “Info.plist” file of Content Extension, add “UNNotificationExtensionDefaultContentHidden” Boolean value with “NO”.

4) Replace “NotificationViewController” class files from the "Rich Files" into your project.
```





### Deployment Over Apple Store

Add Following runscript in your application target ,when you are deploying application over apple store,this run script use remove unused architecture in release mode

```shell

APP_PATH="${TARGET_BUILD_DIR}/${WRAPPER_NAME}"



# This script loops through the frameworks embedded in the application and

# removes unused architectures.

find "$APP_PATH" -name '*.framework' -type d | while read -r FRAMEWORK

do

FRAMEWORK_EXECUTABLE_NAME=$(defaults read "$FRAMEWORK/Info.plist" CFBundleExecutable)

FRAMEWORK_EXECUTABLE_PATH="$FRAMEWORK/$FRAMEWORK_EXECUTABLE_NAME"

echo "Executable is $FRAMEWORK_EXECUTABLE_PATH"



EXTRACTED_ARCHS=()



for ARCH in $ARCHS

do

echo "Extracting $ARCH from $FRAMEWORK_EXECUTABLE_NAME"

lipo -extract "$ARCH" "$FRAMEWORK_EXECUTABLE_PATH" -o "$FRAMEWORK_EXECUTABLE_PATH-$ARCH"

EXTRACTED_ARCHS+=("$FRAMEWORK_EXECUTABLE_PATH-$ARCH")

done


echo "Merging extracted architectures: ${ARCHS}"

lipo -o "$FRAMEWORK_EXECUTABLE_PATH-merged" -create "${EXTRACTED_ARCHS[@]}"

rm "${EXTRACTED_ARCHS[@]}"



echo "Replacing original executable with thinned version"

rm "$FRAMEWORK_EXECUTABLE_PATH"

mv "$FRAMEWORK_EXECUTABLE_PATH-merged" "$FRAMEWORK_EXECUTABLE_PATH"


done
```
