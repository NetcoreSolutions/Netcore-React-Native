

# Smartech React Native SDK

## Install and Integration

1.  `npm install --save smartech-react-native`
2.  `react-native link smartech-react-native`  **or** link the project [manually](./docs/install.md#manual-linking)
3. Follow the [installing instructions](./docs/install.md)
4. Follow the [integration instructions](./docs/integration.md)


## Additional Resources

-  [Smartech Android SDK Integration guide](https://docs.netcoresmartech.com/docs/android-sdk)

-  [Smartech iOS SDK Integration guide](https://docs.netcoresmartech.com/docs/ios-sdk-integration)


## Example JS Usage

### Grab a reference (iOS and Android)

```javascript
const smartech = require('smartech-react-native');
```

#### To set user identity  (iOS and Android)
In order to identify a user, set unique user identity by adding given snippet as per the requirement.

```javascript
smartech.setIdentity(<unique_user_identity>)
    .then(result => {
    //Your code here
}).catch(reason => console.log(reason));

```
#### To clear user identity  (iOS and Android)
In order to wipe out user identity from the SDK, add given snippet as per the requirement.

```javascript
smartech.clearIdentity()
    .then(result => {
    //Your code here
}).catch(reason => console.log(reason));

```

#### To capture user login  (iOS and Android)
To capture login activity of the user, add given snippet inside the login file of your project when the user gets logged in successfully.

```javascript
smartech.login(<unique_user_identity>)
    .then(result => {
    //Your code here
}).catch(reason => console.log(reason));

```

#### To capture user logout  (iOS and Android)

To capture logout activity of the user, add given snippet inside your file when the user gets logged out successfully.

```javascript
smartech.logout()
	.then(result => {
		//Your code here
}).catch(reason => console.log(reason));

smartech.clearIdentity()
    .then(result => {
    //Your code here
}).catch(reason => console.log(reason));

```
****Note:​​**** Avoid calling **clearIdentity()** method if one wants to track user activity even if user has logged out of the application.

#### To capture custom activity  (iOS and Android)

To capture custom activity performed by the user, add given snippet as per the requirement.
```javascript
smartech.track(<event_name>, <variable_name>);

E.g.
const payload =  {
    name: 'Galaxy',
    description: '20gram bars',
    id: '1'
};
smartech.track("Add To Cart",payload);
```

**Note​​:** Keep the key name **payload** only for tracking the custom events.

#### To capture user attributes  (iOS and Android)
To capture and map user attributes, add given snippet as per the requirement.

```javascript
smartech.profile(<profile_object>)
	.then(value => {
		//Your code here
	})
	.catch(reason => {
		//Handle error here
});
E.g.
const profile_data =  {
	NAME: "User Name",
	EMAILID: "abc@xyz.com",
	AGE: "30",
	MOBILE: "4545748"
};
smartech.profile(profile_data)
    .then(value => {
    //Your code here
    })
    .catch(reason => {
    //Handle error here
});
```
**Note:** Use attribute name in capital letters as shown above.

#### To fetch delivered push notifications 

To fetch delivered push notifications, add given snippet as per the requirement.


**Note:** The method returns a **JSON** of delivered push notifications for the user.

**For Android:**
```javascript
smartech.getNotifications(<count>)
    .then(value => {
    value = JSON.stringify(value);
    //Your code here
}).catch(reason => console.log(reason));
```

**For iOS:**
```javascript
smartech.getNotificationsiOS(value => {
//Your code here
})
```
****Note:**** The method returns a **JSON** of delivered push notifications for the user.

#### To opt out user from being tracked (GDPR Policy)  (iOS and Android)

If the end user wants to opt out of being tracked, add given snippet as per the requirement.

```javascript
smartech.optOut(<boolean_flag>);
```


**Note​​:** The method accepts a boolean value.

- If an end user wants to opt out, the flag should be passed as **true**. Once the user opts out, SDK will not be able to track that particular user further and no communications will be received by that user.

- If an end user wants to opt in, the flag should be passed as **false**. Once the user opts in, SDK will be able to track that particular user further and next communications will be received by that user.


#### To implement location tracking (Android)

In order to track user location and pass it further to Smartech, add given snippet as per the requirement.

```javascript
smartech.setUserLocation(<double_lat>, <double_long>);
```
****Note:****  

1) The method mentioned above accepts 2 parameters including context, latitude & longitude. **Data type of ‘latitude’ & ‘longitude’ should compulsorily be 'Float'**. In case if any parameter is null, SDK will not be able to persist user location.

2) This method is not for **iOS**.

#### To get GUID of the user  (iOS and Android)

To obtain GUID of the user from the SDK, add given snippet as per the requirement.
```javascript
smartech.getGUID()
    .then(value => {
    //Your code here
}).catch(reason => console.log(reason));

```
#### To get device token of the user  (iOS and Android)

To obtain the FCM token for Android and APNS token for iOS of the user, add given snippet as per the requirement.
```javascript
smartech.getPushToken()
    .then(value => {
    //Your code here
}).catch(reason => console.log(reason));
```

