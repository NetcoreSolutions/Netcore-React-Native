# Installing Smartech React Native

1.  `npm install --save smartech-react-native`

2. `react-native link smartech-react-native` **or** [follow the manual linking instructions below](#manual-linking).

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
classpath 'com.google.gms:google-services:3.1.0'
classpath​ ​'com.android.tools.build:gradle:3.1.3'
```

##### 3. Adding dependencies in the build.gradle file of the app

```java
implementation 'in.netcore.smartechfcm:smartech-fcm:1.1.7'
implementation 'com.google.firebase:firebase-core:16.0.1'
implementation 'com.google.firebase:firebase-messaging:17.3.4'
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.firebase:firebase-jobdispatcher:0.8.5'
```

##### 4. Adding below code in the ​ build.gradle file of the app​​.
```java
apply plugin: 'com.google.gms.google-services'
```

## Manual Linking

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