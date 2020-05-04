//
//  SmartechManager.h
//  NetCorePush
//
//  Created by Smartech on 21/03/20.
//  Copyright © 2020 Netcore Solutions. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UserNotifications/UserNotifications.h>
#import <UserNotificationsUI/UserNotificationsUI.h>
#import "NCNotification.h"


NS_ASSUME_NONNULL_BEGIN

@protocol SmartechDelegate<NSObject>
@optional

- (void)handleDeeplinkActionWithURLString:(NSString *)deepLinkURLString
                         andCustomPayload:(NSDictionary *_Nullable)customPayload;
@end

@interface Smartech : NSObject

@property(nonatomic,weak) id <SmartechDelegate>delegate;

/**
 @return Smartech instance.
 */
+ (instancetype)sharedInstance;

#pragma mark - Initialise SDK
/**
 delclare delegate inorder support protocol delegation
 */

-(void)initSDKWithDelegate:(id<SmartechDelegate>)delegate withLaunchOptions:launchOptions;

#pragma mark - Notification Methods

/**
 @brief Tells the delegate that the app successfully registered with Apple Push Notification service.
 
 @param deviceToken - the unique device token recieved from APNS.
 */
- (void)didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken;

/**
 @brief Apple Push Notification service cannot successfully complete the registration process
 
 @param error - the error on APNS registration request.
 */
- (void)didFailToRegisterForRemoteNotificationsWithError:(NSError *)error;

/**
 @brief Receive a dictionary that contains badge number, an alert sound, an alert message to display to the user from Apple Push Notification service.
 
 @param userInfo - the notification userInfo dictionary.
 */
- (void)didReceiveRemoteNotification:(NSDictionary *)userInfo;

#pragma mark - Local Notification Method for iOS version 8 and 9

- (void)didReceiveLocalNotification:(NSDictionary *)userInfo;

#pragma mark - Notification Methods for iOS version 10 and above
/**
 @brief The method will be called on the delegate only if the application is in the foreground.The application can choose to have the notification presented as a sound, badge, alert and/or in the notification list.
 
 @param notification - the notification object.
 */
- (void)willPresentForegroundNotification:(UNNotification *)notification API_AVAILABLE(ios(10.0));

/**
 @brief The method will be called on the delegate when the user responded to the notification by opening the application, dismissing the notification or choosing a UNNotificationAction.
 
 @param response - the notificaiton response.
 */
- (void)didReceiveNotificationResponse:(UNNotificationResponse *)response API_AVAILABLE(ios(10.0));

#pragma mark - Notification Service Extension methods

- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request
                   withContentHandler:(void (^)(UNNotificationContent *contentToDeliver))contentHandler API_AVAILABLE(ios(10.0));

/**
 @brief This method will be called just before this extension is terminated by the system. You may choose whether to override this method.
 */
-(void)serviceExtensionTimeWillExpire;

#pragma mark - Notification Content Extension methods

/**
 @brief This method used to load the carousel view for the push notification.
 
 @param view - view object
 */
- (void)loadCustomNotificationContentView:(UIView *)view;

/**
 @brief Method will call when the user made 3D touch on the notification, This method for displaying Carousel Notification.
 
 @param notification - notification object.
 */
- (void)didReceiveCustomNotification:(UNNotification *)notification API_AVAILABLE(ios(10.0));

/**
 @brief This method used to call when the user taps on one of the notification action buttons. The completion handler can be called after handling the action to dismiss the notification and forward the action to the app if necessary for Carousel Push Notification.
 
 @param response - response object.
 @param completion - completionHandler object to handle once the response handling is done.
 */
- (void)didReceiveCustomNotificationResponse:(UNNotificationResponse *)response completionHandler:(void (^)(UNNotificationContentExtensionResponseOption option))completion API_AVAILABLE(ios(10.0));

#pragma mark - Notification Center Methods

/**
 @brief The method will be called to get the unread notification count.
 
 @return NSInteger - the notificaiton unread count.
 */
- (NSInteger)getUnreadNotificationsCount;

/**
 @brief This method fetch App Inbox notifications.
 */
- (NSArray <NCNotification *>*)getNotifications;

/**
 @brief This method fetch smartech notifications.
 
 @param count -  notification count
 
 @return NSArray - get notifications according to desired count.
 */
- (NSArray <NCNotification *>*)getNotificationsWithCount:(NSInteger)count;

/**
 @brief This method deletes the notification.
 
 @param arrayOfIds - the array of notification ids / trids.
 */
- (void)deleteNotification:(NSArray *)arrayOfIds;

/**
 @brief This method will mark the notification as read and send a click event to the server.
 
 @param userInfo - The notification payload.
 @param shouldHandle - This value will indicate whether the deeplink is to be handled or not.
 @param deeplink - The deeplink string from the notification payload.
 */
- (void)trackNotificationOpenEvent:(NSDictionary *)userInfo shouldHandleDeeplink:(BOOL)shouldHandle withDeeplink:(NSString *)deeplink;

#pragma mark - User Tracking Methods

/**
 @brief This method would login the user on Smartech.
 
 @param userIdentity - the user identity.
 */
- (void)login:(NSString *)userIdentity;

/**
 @brief This method would set the user identity.
 
 @param userIdentity - the user identity.
 */
- (void)setUserIdentity:(NSString *)userIdentity;

/**
 @brief This method would get the user identity.
 
 @return NSString - the user identity.
 */
- (NSString *)getUserIdentity;

/**
 @brief This method would logout the user and clear identity on Smartech.
 
 @param clearUserIdentity - to clear user identity for all events if the value is set as true/YES.
 */
- (void)logoutAndClearUserIdentity:(BOOL)clearUserIdentity;

/**
 @brief This method would clear the user identity.
 */
- (void)clearUserIdentity;

/**
 @brief This method is used to update the user profile.
 
 @param payloadDictionary - extra data to be evaluated while tracking.
 */
- (void)updateUserProfile:(NSDictionary *)payloadDictionary;

#pragma mark - Event Tracking Method
/**
 @brief This method is used to track event.
 
 @param eventName - name of the event for tracking.
 @param payloadDictionary - extra data to be evaluated while tracking.
 */
- (void)trackEvent:(NSString *)eventName andPayload:(NSDictionary *_Nullable)payloadDictionary;

#pragma mark - GDPR Methods

/**
 @brief This method will stop tracking of events, displaying any kind of notifications and in-apps
 
 @param status - BOOL value to enable or disable tracking of events, displaying any kind of notifications and in-apps
 */
- (void)setOptOutStatus:(BOOL)status;

/**
 @brief This method is used to get the status of GDPR.
 
 @return String - the BOOL value which is set.
 */
- (BOOL)getOptOutStatus;

#pragma mark - User Device Details
/**
 @brief This method is used to get device push token.
 
 @return String - the device token.
 */
- (NSString *)getDevicePushToken;

/**
 @brief This method is used to print device push token.
 */
- (void)printDevicePushToken;

/**
 @brief This method is used to get device unique identifier.
 
 @return String - the device unique id.
 */
- (NSString *)getDeviceGuid;

/**
 @brief This method will check that the notification is from Smartech or not.
 
 @param userInfo - the notification payload.
 
 @return BOOL - the result of whether the notification from Smartech or not.
 */
- (BOOL)isNotificationFromSmartech:(NSDictionary *)userInfo;

@end

NS_ASSUME_NONNULL_END
