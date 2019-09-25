
[![Netcore Logo](https://netcore.in/wp-content/themes/netcore/img/Netcore-new-Logo.png)](http:www.netcore.in)

# Netcore iOS SDK
Here's a quick run through on getting started with Netcore SDK in Swift, for detailed integration steps visit the [Official Docs.](https://docs.netcoresmartech.com/docs/ios-sdk-integration)


## Integration using CocoaPod
1. Install CocoaPods on your computer.

2. Open your project and create pod file using below command
```swift
pod init
```
3. Add following line in your podfile
```swift
pod 'Netcore-Smartech-iOS-SDK'
```

4. Run following command in your project directory
```swift
pod install
```

5. Add Following capability inside your application
```swift
Push Notification
Background Mode -> Remote Notification
App Groups
```

6. Open App.xcworkspace and build.

## NetCore Manual Integration
1. Download iOS SDK and Unzip the file. Open Framework folder - inside it you will
see NetCorePush.framework file.
2. Open existing or create a new project in Xcode and drag drop or add framework
in Target > Embedded Binaries section
3. Add following frameworks inside your application if required
```swift
Security
CoreLocation
SystemConfiguration
JavaScriptCore 
```
4. Add Following capability inside your application
```swift
Push Notification
Background Mode -> Remote Notification
App Groups
```
5. Create Bridge file in existing swift project if required and add Following code inside file.
```objc
#import <NetCorePush/NetCorePush.h>
```

## NetCore SDK Initialization
1. Import following file in App Delegate File
```swift
import UserNotifications
import UserNotificationsUI
import NetCorePush
```
2. Add NetCore Application AppID in support in Finish Launching Methods
(AppDelegate file)
```swift
let appGroup = "<group.com.CompanyName.ProductName>"

NetCoreSharedManager.sharedInstance().setUpAppGroup(appGroup)

let netCore_AppID = "your App Id which you get from Netcore smartech admin panel"
// Set up NetCore  Application Id

NetCoreSharedManager.sharedInstance().handleApplicationLaunchEvent(launchOptions, forApplicationId: netCore_AppID)

//set up push delegate
NetCorePushTaskManager.sharedInstance().delegate = self
```
3. Register Device With NetCore SDK (AppDelegate file)
```swift
func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {

    //Identity must be “”(blank) or as per Primary key which defined on smartech Panel
    NetCoreInstallation.sharedInstance().netCorePushRegisteration(Identity, withDeviceToken: deviceToken) { (status) in }
}
```
## For Normal Push Notifications

```swift
//Handle Remote/Local Notification Delegate Events (AppDelegate file)
func application ( _ application : UIApplication, didReceiveRemoteNotification userInfo : [ AnyHashable : Any ]) {
    // perform notification received/click action as per third party SDK as per their document
    NetCorePushTaskManager.sharedInstance().didReceiveRemoteNotification(userInfo)
}

func application (_ application : UIApplication , didReceive notification : UILocalNotification ){
    NetCorePushTaskManager.sharedInstance().didReceiveLocalNotification(notification.userInfo)
}

```

```swift
// Adding UNUserNotificationCenter Delegate Method (v2.3.6 onwards)
extension AppDelegate: UNUserNotificationCenterDelegate {
@available ( iOS 10.0 , * )
func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
    completionHandler(UNNotificationPresentationOptions.alert);
}

@objc (userNotificationCenter: didReceiveNotificationResponse :withCompletionHandler:)

func userNotificationCenter ( _ center : UNUserNotificationCenter, didReceive
response : UNNotificationResponse, withCompletionHandler completionHandler :
@escaping () -> Void ) {
        // perform notification received/click action as per third party SDK as per their document
        NetCorePushTaskManager.sharedInstance()?.didReceiveRemoteNotification(response.notification.request.content.userInfo);
    }
}
```

## To Handle URL Link

```swift
func application(_ application: UIApplication, open url: URL, sourceApplication: String?, annotation: Any) -> Bool {
    if url.absoluteString.lowercased().contains ("your app URL link") {
            // handle URL link here
        }
    return true
}
```

## To Setting Universal Link
You need to pass the universal link value to the SDK that you have added in your Capabilities -> Associated Domains section
Eg. applinks:https://www.netcoresmartech.com then enter only domain name as https://www.netcoresmartech.com, you can pass multiple domain names.
```swift
NetCoreSharedManager.sharedInstance()?.setAssociateDomain(["type-your-universal-link"]);
```

## To Handle Deep Link and Custom Payload (2.3.6 onwards)
```swift
func handleSmartechDeeplink(_ options: SMTDeeplink?) {
    if options?.deepLinkType == SMTDeeplinkType.url {
        // When Deeplink is WebURL
    }
    else if options?.deepLinkType == SMTDeeplinkType.universalLink {
        // When Deeplink is Universal-link
    }
    else if options?.deepLinkType == SMTDeeplinkType.deeplink {
        // When Deeplink is URLSchemes link.
    }
    else if options?.deepLinkType == SMTDeeplinkType.app {
        // When Deeplink is Empty.
    }

    // options?.customPayload is send from notification.

    // options?.userInfo is notification Pyaload.


}
```

## To Handle Deep Link

```swift
//For Handling deep link
extension AppDelegate : NetCorePushTaskManagerDelegate {
    func handleNotificationOpenAction(_ userInfo: [AnyHashable : Any]!, deepLinkType strType: String!) {
        if strType .lowercased().contains ("your app deep link"){
            // handle deep link here
        }
    }
}
```

## To Handle Custom Payload

```swift
//For Handle custom payload
extension AppDelegate : NetCorePushTaskManagerDelegate {
    func handleNotificationCustomPayload(_ payload: [AnyHashable : Any]!) {

    }
}
```
## To Handle Interactive buttons

```swift
func application(_ application: UIApplication, handleActionWithIdentifier identifier:
String?, forRemoteNotification userInfo: [AnyHashable : Any], withResponseInfo
responseInfo: [AnyHashable : Any], completionHandler: @escaping () -> Void) {

    NetCorePushTaskManager.sharedInstance().handleAction(withIdentifier: identifier, forRemoteNotification: userInfo,withResponseInfo: responseInfo)
    completionHandler()

}
```


## For Login Activity
```swift
// Identity must be “”(blank) or as per Primary key which defined on smartech Panel
NetCoreInstallation.sharedInstance().netCorePushLogin(Identity) {(statusCode:Int) in }
```

## For Logout Activity
```swift
NetCoreInstallation.sharedInstance().netCorePushLogout { (statusCode:Int) in }
```
## For Profile Update
```swift
// Identity must be “”(blank) or as per Primary key which defined on smartech Panel
let info = ["NAME":"Tester", "AGE":"23", "MOBILE":"9898948849"]

NetCoreInstallation.sharedInstance().netCoreProfilePush(Identity, payload: info, block: nil)
//Attribute name must be in Capital such as NAME, AGE etc.

```
## To Track Custom Event
```swift
//add To cart event ID with custom array of data
NetCoreAppTracking.sharedInstance().sendEvent(withCustomPayload:Int(UInt32(tracking_AddToCart.rawValue)), payload: arrayAddToCart , block: nil)

//event name with custom payload dictionary of data
NetCoreAppTracking.sharedInstance()?.trackEvent(withCustomPayload: Event_Name,payload: payloadDict, block:nil)
for eg.,
let payloadDict = NSMutableDictionary()
payloadDict.setValue("iPhonex", forKey: "name")

let array = Array(arrayLiteral: "1","2","3");
payloadDict.setValue(array, forKey: "Price")

let details = NSMutableDictionary()
details.setValue(“iphone XS", forKey: "name");
payloadDict.setValue(details, forKey: "details")

NetCoreAppTracking.sharedInstance()?.trackEvent(withCustomPayload: "Add to cart", payload: payloadDict, block:nil)

```
## To Fetch Delivered push notifications
```swift
let notificationArray : Array = NetCoreSharedManager.sharedInstance().getNotifications()

// To get recent 'n' number of notifications
let notificationArray : Array = NetCoreSharedManager.sharedInstance().getNotificationsWithCount(<count>)
```

## If user wants to opt out from being tracked
Add below code
```swift
NetCoreSharedManager.sharedInstance().optOut(<boolean_flag>)
```
Note:  The method mentioned above accepts a compulsory boolean value (true/false).


- If an end user wants to opt out, the flag should be passed as **true**. Once the user opts out, Netcore SDK will not be able to track that particular user further and no communications will be received by that user. </br>
**e.g. NetCoreSharedManager.sharedInstance().optOut(true)**

- If an end user wants to opt in, the flag should be passed as **false**. Once the user opts in, Netcore SDK will be able to track that particular user further and next communications will be received by that user.</br>
**e.g NetCoreSharedManager.sharedInstance().optOut(false)**

## To Clear Identity
```swift
NetCoreSharedManager.sharedInstance()?.clearIdentity()
```
Note: The method clears the identity locally and all the event carried out after this call will be treated as Anonymous user activity.

## To Get Device Token
```swift
NetCoreSharedManager.sharedInstance()?.getDeviceToken()
```

## To Get GUID
```swift
NetCoreSharedManager.sharedInstance()?.getGUID()
```

## For Rich Push Notifications 
### Configuration Changes
```swift
1) Add “Notification Service Extension” to your app. File->New->Target- >Notification Service Extension.

2) Click Next and when asked to “Activate”, Click Activate.

3) Add “App Groups” to your apps Capabilities(Add one group with name "<group.com.CompanyName.ProductName>").

4) Enable App groups in Service Extension too and select group with name "<group.com.CompanyName.ProductName>".

5) If App group is not activated on the provisioning profile you are using, then 
i.Enable App groups in your provisioning profile from your Apple Developer’s account and replace the profile with the new one. Or,
ii.In your apps’s, Target->General-> Signing, Select “Automatically manage signing” and enable App groups by going to Target->Capabilities->App group. This will automatically add app groups capability to you provisioning profile.
```
***NOTE: For more clarity on this, please refer above <[SDK-Integration-Steps.pdf](https://github.com/NetcoreSolutions/Smartech-ios-sdk/blob/master/ObjectiveC-and-swift-Integration-Steps.pdf)>**

### Implementation Changes

Remove all the code written in “NotificationService” implementation class.

1) Import NetCore Framework into Extension
```swift
import NetCorePush
```
2) Handle Notification Request
```swift
override func didReceive(_ request: UNNotificationRequest, withContentHandler contentHandler: @escaping (UNNotificationContent) -> Void) {
    let appGroup = "<group.com.CompanyName.ProductName>"
    NetCoreNotificationService.sharedInstance().setUpAppGroup(appGroup)

    NetCoreNotificationService.sharedInstance().didReceive(request) { (contentToDeliver:UNNotificationContent) in
    contentHandler(contentToDeliver) }
}
```
3) Handle Notification Service Time Expire
```swift
override func serviceExtensionTimeWillExpire() { 
    NetCoreNotificationService.sharedInstance().serviceExtensionTimeWillExpire() 
}
```


## Notification Center Feature

**Note:​​**  To use Notification Center minimum Netcore SDK version should be **2.3.5**. 

To use Notification Center feature in your app, please follow the steps mentioned below:

1. Copy Notification Center files in your project. (SmartechNC folder)
2. Create Bridge file in existing swift project if required and add Following code inside file.

```objc
#import "SmartechNotificationCenterVC.h"
```

3. Use this code to open Notification Center View Controller

```swift
let center = UIStoryboard.init(name: "SmartechNC", bundle: nil).instantiateViewController(withIdentifier: "SmartechNotificationCenterVC")
self.navigationController?.pushViewController(center, animated: true)
```
5. To get all the delivered notifications to the device.
```swift
let array = SMTNotification.getNotifications()
```
4. To get last X number of delivered notifications (the below code will fetch the last 10 notifications).
```swift
let array = SMTNotification.getNotificationsWithCount(10)
```
5. To get Unread delivered notification count.
```swift
let count = SMTNotification.getUnreadNotificationsCount();
```





```
***NOTE: For more clarity on this, please refer above <[SDK-Integration-Steps.pdf](https://github.com/NetcoreSolutions/Smartech-ios-sdk/blob/master/ObjectiveC-and-swift-Integration-Steps.pdf)>**


## For Carousel Push Notifications 
### Configuration Changes
```swift
1) Add “Notification Content Extension” to your app. File->New->Target->Notification Content Extension.

2) Click Next and when asked to “Activate”, Click Activate.

3) Add “App Groups” to your apps Capabilities(Add one group with name "<group.com.CompanyName.ProductName>").

4) Enable “App Groups” in Content Extension too and select group with name "<group.com.CompanyName.ProductName>".

```
***NOTE: For more clarity on this, please refer above <[SDK-Integration-Steps.pdf](https://github.com/NetcoreSolutions/Smartech-ios-sdk/blob/master/ObjectiveC-and-swift-Integration-Steps.pdf)>**

### Implementation Changes
```swift
1) Replace “MainInterface.storyboard” of Content Extension with the one provided in "Rich Files".

2) In “Info.plist” file of Content Extension, replace “UNNotificationExtensionCategory” value with “SmartechPushCategory”.

3) In “Info.plist” file of Content Extension, add “UNNotificationExtensionDefaultContentHidden” Boolean value with “NO”.

4) Replace “NotificationViewController” class files from the "Rich Files" into your project.
```
***NOTE: For more clarity on this, please refer above <[SDK-Integration-Steps.pdf](https://github.com/NetcoreSolutions/Smartech-ios-sdk/blob/master/ObjectiveC-and-swift-Integration-Steps.pdf)>**

### Deployment Over Apple Store
Add Following runscript in your application target ,when you are deploying application
over apple store,this run script use remove unused architecture in release mode
```swift
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

