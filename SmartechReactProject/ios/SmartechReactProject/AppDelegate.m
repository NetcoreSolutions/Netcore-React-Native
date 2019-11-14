/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"
#import "DeeplinkManager.h"

#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <NetCorePush/NetCorePush.h>

@interface AppDelegate () <NetCorePushTaskManagerDelegate, UNUserNotificationCenterDelegate>

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  RCTBridge *bridge = [[RCTBridge alloc] initWithDelegate:self launchOptions:launchOptions];
  RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:bridge
                                                   moduleName:@"SmartechReactProject"
                                            initialProperties:nil];
  
  rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];
  
  [[NetCoreSharedManager sharedInstance] setUpAppGroup:@"group.com.netcore.NetcoreApp"];
  [[NetCoreSharedManager sharedInstance] handleApplicationLaunchEvent:launchOptions forApplicationId:@"8e289ded3f21cc6e20563c87a0f0374d"];
  [NetCorePushTaskManager sharedInstance].delegate = (id)self;
  [UNUserNotificationCenter currentNotificationCenter].delegate = self;
  
  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  UIViewController *rootViewController = [UIViewController new];
  rootViewController.view = rootView;
  self.window.rootViewController = rootViewController;
  [self.window makeKeyAndVisible];
  return YES;
}

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
#if DEBUG
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
#else
  return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
  [[NetCoreInstallation sharedInstance] netCorePushRegisteration:[[NetCoreSharedManager sharedInstance] getIdentity] withDeviceToken:deviceToken Block:^(NSInteger statusCode) {
    NSLog(@"statusCode : %ld",(long)statusCode);
  }];
  [[NetCoreSharedManager sharedInstance] printDeviceToken];
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler {
  [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:userInfo];
  completionHandler(UIBackgroundFetchResultNewData);
}

#pragma mark - UNUserNotificationCenterDelegate
- (void)userNotificationCenter:(UNUserNotificationCenter *)center willPresentNotification:(UNNotification *)notification withCompletionHandler:(void (^)(UNNotificationPresentationOptions options))completionHandler {
  [[NetCorePushTaskManager sharedInstance] userNotificationWillPresentNotification:notification];
  completionHandler(UNNotificationPresentationOptionAlert | UNNotificationPresentationOptionBadge | UNNotificationPresentationOptionSound);
}

- (void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)(void))completionHandler {
  [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:response.notification.request.content.userInfo];
  completionHandler();
}

#pragma mark - NetCorePushTaskManagerDelegate
- (void)handleSmartechDeeplink:(SMTDeeplink *)options {
  [DeeplinkManager emitEventWithDeeplink:options.deepLink customPayload:options.customPayload andPayload:options.userInfo];
}

@end
