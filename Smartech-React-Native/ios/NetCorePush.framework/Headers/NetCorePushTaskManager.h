/*
 @header NetCorePushTaskManager.h
 
 @brief NetCorePushTaskManager : -  use to setup all pushHandler event
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.9 */
#import <Foundation/Foundation.h>
#import <UserNotifications/UserNotifications.h>
#import <UserNotificationsUI/UserNotificationsUI.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSUInteger, SMTDeeplinkType) {
    SMTDeeplinkTypeApp,
    SMTDeeplinkTypeUrl,
    SMTDeeplinkTypeDeeplink,
    SMTDeeplinkTypeUniversalLink,
};


@interface SMTDeeplink : NSObject

@property (nonatomic) SMTDeeplinkType deepLinkType;

@property (nonatomic, nonnull) NSDictionary *customPayload;

@property (nonatomic, strong) NSString *_Nullable deepLink;

@property (nonatomic, strong) NSDictionary *_Nullable userInfo;

@end



/*
  define protocol to return push notifcation deeplinking event
 */
@protocol NetCorePushTaskManagerDelegate<NSObject>
@optional

-(void)handleNotificationOpenAction:(NSDictionary *_Nullable)userInfo DeepLinkType:(NSString *_Nullable)strType;

-(void)handleNotificationCustomPayload:(NSDictionary *_Nullable)payload;

- (void)handleSmartechDeeplink:(SMTDeeplink *_Nullable)options;

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
-(void)handelApplicationLaunchEvent:(id _Nullable)launchOptions;

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
-(void)userNotificationdidReceiveNotificationResponse:(UNNotificationResponse *)response API_AVAILABLE(ios(10.0));

/*
 @Method userNotificationWillPresentNotification :- use to manage notification present event(onward ios 10)
 */
-(void)userNotificationWillPresentNotification:(UNNotification *)notification API_AVAILABLE(ios(10.0));
/*
 @Method didReceiveNotificationRequest :- use to manage notification request(onward ios 10)
 */
-(void)didReceiveNotificationRequest:(UNNotificationRequest *)request API_AVAILABLE(ios(10.0));
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

/*
 @Method sendOpenEventFor :- use to send open event for a notification from notification centre if its open is not sent.
 */

- (void)markNotificationAsRead:(NSDictionary *)messageDict autoHandleDeeplink:(BOOL)shouldHandle;


- (BOOL)deleteNotification:(NSArray *)notificationIDs;

NS_ASSUME_NONNULL_END

@end
