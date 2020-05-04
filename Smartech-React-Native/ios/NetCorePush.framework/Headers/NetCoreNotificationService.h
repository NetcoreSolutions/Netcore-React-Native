/*
 @header NetCoreNotificationService.h
 
 @brief NetCoreNotificationService : - use to provides the entry point for a Notification Service
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.5.0 */

#import <UserNotifications/UserNotifications.h>
#import <UserNotifications/UNNotificationContent.h>
#import <UserNotificationsUI/UserNotificationsUI.h>
#import <UIKit/UIKit.h>

API_AVAILABLE(ios(10.0))
@interface NetCoreNotificationService : UNNotificationServiceExtension
/*
 @Method sharedInstance:- use to return NetCoreNotificationService manage class Object
 @return NetCoreNotificationService instance
 */
+ (instancetype)sharedInstance;
/*
 @Method contentViewDidLoad :- use to load the view controller for Carousel Push Notification.
 */
-(void)contentViewDidLoad:(UIView *)customView;
/*
 @Method didReceiveNotification :- use to call the notification to be displayed on the extension for Carousel Push Notification.
 */
-(void)didReceiveNotification:(UNNotification *)notification;
/*
 @Method didReceiveNotificationResponse :- use to call when the user taps on one of the notification action buttons. The completion handler can be called
     after handling the action to dismiss the notification and forward the
     action to the app if necessary for Carousel Push Notification.
 */
-(void)didReceiveNotificationResponse:(UNNotificationResponse *)response completionHandler:(void (^)(UNNotificationContentExtensionResponseOption))completion;
/*
 @Method setUpAppGroup: this method use to setup app groups
 @param  appGroup - contain appGroup string. Usually, group.<your-bundle-identifier>. eg., group.com.CompanyName.productName
 */
-(void)setUpAppGroup:(NSString *)appGroup;

@end

