


# Installing Smartech React Native

1.  `npm install --save smartech-react-native`

2. `react-native link smartech-react-native` **or** [follow the manual linking instructions below](#manual-linking).

## iOS
- Add `pod 'Netcore-Smartech-iOS-SDK'` as a dependency in your ios/Podfile. [See an example Podfile here](https://github.com/NetcoreSolutions/Smartech-ios-sdk).
-  `cd ios; pod install --repo-update`

- Note that after pod install, open your project using **[MyProject].xcworkspace** instead of the original .xcodeproj.


## Android

### Prerequisites

##### 1. google-services.json file from [Firebase Console](https://console.firebase.google.com/)

##### 2. Server Key​​ from ​Firebase Console

##### 3. App Id from Smartech Panel
****Note:**** Minimum SDK version (minSdkVersion) available in the build.gradle file of the app should be at least 16 (Jelly Bean) or above.


### Setting up FCM in the native android application:

##### 1. Add google-services.json file in /ReactNativeProject/android/app directory of the project.
##### 2. Adding dependencies given below in ​ build.gradle file of the project​.​

```java
classpath 'com.android.tools.build:gradle:3.3.1'
classpath 'com.google.gms:google-services:4.2.0'
```

##### 3. Adding dependencies in the build.gradle file of the app

```java
implementation 'in.netcore.smartechfcm:smartech-fcm:1.2.0'
implementation 'com.google.firebase:firebase-messaging:17.3.4'
implementation 'com.google.code.gson:gson:2.8.0'
implementation 'com.google.android.gms:play-services-ads:17.1.1'
```

##### 4. Adding below code in the ​ build.gradle file of the app​​.
```java
apply plugin: 'com.google.gms.google-services'
```

## Manual Linking

### iOS:

- Drag and Drop node_modules/smartech-react-native/ios/smartechReact.xcodeproj into the Libraries folder of your project in XCode ([see Step 1 here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html#manual-linking)).

- Drag and Drop the libsmartechReact.a product in smartechReact.xcodeproj into your project's target's "Link Binary With Libraries" section ([see Step 2 here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html#manual-linking)).

- Add a Header Search Path pointing to `$(SRCROOT)/../node_modules/smartech-react-native/ios` ([see Step 3 here](http://facebook.github.io/react-native/docs/linking-libraries-ios.html#manual-linking)).

### Android:

**android/settings.gradle**
```gradle
include ':smartech-react-native'

project(':smartech-react-native').projectDir = new File(rootProject.projectDir, '../node_modules/smartech-react-native/android')
```

**android/app/build.gradle**
```gradle
dependencies {
...
	implementation project(':smartech-react-native')
}
```

Now move on to [integrating the SDK](./integration.md). 
