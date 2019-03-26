/*
 @header NetCorePushTaskManager.h
 
 @brief NetCorePushTaskManager : -  use to setup all pushHandler event
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.4 */
#import <Foundation/Foundation.h>
#import <UserNotifications/UserNotifications.h>
#import <UserNotificationsUI/UserNotificationsUI.h>

/*
  define protocol to return push notifcation deeplinking event
 */
@protocol NetCorePushTaskManagerDelegate<NSObject>
@optional
-(void)handleNotificationOpenAction:(NSDictionary *)userInfo DeepLinkType:(NSString *)strType;

-(void)handleNotificationCustomPayload:(NSDictionary *)payload;
@end

@interface NetCorePushTaskManager : NSObject

/*
 @Method sharedInstance:- use to return NetCorePushTaskManager manage class Object
 @param  -
 @return NetCorePushTaskManager instance
 */
+ (instancetype)sharedInstance;

/*
 delare delegate inorder support protocol delegation
 */
@property(nonatomic,weak) id <NetCorePushTaskManagerDelegate>delegate;

/*
  @Method handelApplicationLaunchEvent :- use to handle push notification open event when application not launched
 */
-(void)handelApplicationLaunchEvent:(id)launchOptions;

/*
 @Method didReceiveRemoteNotification :- use to handle push notification event (before ios 10)
 */
-(void)didReceiveRemoteNotification:(NSDictionary *)userInfo;
/*
 @Method didReceiveLocalNotification :- use to handle local notification event (before ios 10)
 */
-(void)didReceiveLocalNotification:(NSDictionary *)userInfo;

/*
 @Method userNotificationdidReceiveNotificationResponse :- use to manage notification tap/open event(onward ios 10)
 */
-(void)userNotificationdidReceiveNotificationResponse:(UNNotificationResponse *)response;

/*
 @Method userNotificationWillPresentNotification :- use to manage notification present event(onward ios 10)
 */
-(void)userNotificationWillPresentNotification:(UNNotification *)notification;
/*
 @Method didReceiveNotificationRequest :- use to manage notification request(onward ios 10)
 */
-(void)didReceiveNotificationRequest:(UNNotificationRequest *)request;
/*
 @Method handleActionWithIdentifier :- use to manage Action buttons for Remote Notifications and their identifiers
 */
-(void)handleActionWithIdentifier:(NSString *)identifier forRemoteNotification:(NSDictionary *)userInfo withResponseInfo:(NSDictionary *)responseInfo;
/*
 @Method handleActionWithIdentifier :- use to manage Action buttons for Local Notifications and their identifiers
 */
-(void)handleActionWithIdentifier:(NSString *)identifier forLocalNotification:(NSDictionary *)userInfo withResponseInfo:(NSDictionary *)responseInfo;
/*
 @Method isNotificationFromNetCore :- use to check whether the received notification is from NetCore or not.
 */
-(BOOL)isNotificationFromNetCore:(NSDictionary *)userInfo;

@end
