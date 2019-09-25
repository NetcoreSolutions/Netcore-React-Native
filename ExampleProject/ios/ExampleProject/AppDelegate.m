/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"
#import "DeeplinkManager.h"

#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <NetCorePush/NetCorePush.h>
#import <UserNotifications/UserNotifications.h>
#import <UserNotificationsUI/UserNotificationsUI.h>

@interface AppDelegate () <UNUserNotificationCenterDelegate, NetCorePushTaskManagerDelegate>
@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  NSURL *jsCodeLocation;

  #ifdef DEBUG
    jsCodeLocation = [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
  #else
    jsCodeLocation = [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
  #endif

  RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
                                                      moduleName:@"ExampleProject"
                                               initialProperties:nil
                                                   launchOptions:launchOptions];
  
  [[NetCoreSharedManager sharedInstance] setUpAppGroup:@"<APP_GROUP>"];
  [[NetCoreSharedManager sharedInstance] handleApplicationLaunchEvent:launchOptions forApplicationId:@"<APP_ID>"];
  [NetCorePushTaskManager sharedInstance].delegate = (id)self;
  
  
  rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  UIViewController *rootViewController = [UIViewController new];
  rootViewController.view = rootView;
  self.window.rootViewController = rootViewController;
  [self.window makeKeyAndVisible];
  return YES;
}

- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
  [[NetCoreInstallation sharedInstance] netCorePushRegisteration:[[NetCoreSharedManager sharedInstance] getIdentity] withDeviceToken:deviceToken Block:nil];
  [[NetCoreSharedManager sharedInstance] printDeviceToken];
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler {
  [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:userInfo];
  completionHandler(UIBackgroundFetchResultNewData);
}

#pragma mark UNUserNotificationCenterDelegate
- (void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions options))completionHandler {
  [[NetCorePushTaskManager sharedInstance] userNotificationWillPresentNotification:notification];
  completionHandler(UNNotificationPresentationOptionAlert | UNNotificationPresentationOptionBadge | UNNotificationPresentationOptionSound);
}

- (void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)(void))completionHandler {
  [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:response.notification.request.content.userInfo];
  completionHandler();
}

//For Handling deep link
- (void)handleSmartechDeeplink:(SMTDeeplink *_Nullable)options {
  [DeeplinkManager emitEventWithDeeplink:options.deepLink customPayload:options.customPayload andPayload:options.userInfo];
}

@end
