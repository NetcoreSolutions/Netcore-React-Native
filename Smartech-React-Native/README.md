
# smartech-react-native

## Getting started

`$ npm install smartech-react-native --save`

### Mostly automatic installation

`$ react-native link smartech-react-native`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `smartech-react-native` and add `SMTSmartechReactNative.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libSMTSmartechReactNative.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.smartech.reactnative.SMTSmartechReactNativePackage;` to the imports at the top of the file
  - Add `new SMTSmartechReactNativePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':smartech-react-native'
  	project(':smartech-react-native').projectDir = new File(rootProject.projectDir, 	'../node_modules/smartech-react-native/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':smartech-react-native')
  	```


## Usage
```javascript
import SMTSmartechReactNative from 'smartech-react-native';

// TODO: What to do with the module?
SMTSmartechReactNative;
```
  