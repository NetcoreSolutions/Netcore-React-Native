/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"

#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <NetCorePush/NetCorePush.h>

@interface AppDelegate () <NetCorePushTaskManagerDelegate>
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
  
  [[NetCoreSharedManager sharedInstance] setUpAppGroup:@""];
  
  [[NetCoreSharedManager sharedInstance] handleApplicationLaunchEvent:launchOptions forApplicationId:@""];
  [NetCorePushTaskManager sharedInstance].delegate = (id)self;
  
  
  rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  UIViewController *rootViewController = [UIViewController new];
  rootViewController.view = rootView;
  self.window.rootViewController = rootViewController;
  [self.window makeKeyAndVisible];
  return YES;
}

-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken {
  [[NetCoreInstallation sharedInstance] netCorePushRegisteration:[[NetCoreSharedManager sharedInstance]getIdentity] withDeviceToken:deviceToken Block:^(NSInteger statusCode) {
    
  }];
  [[NetCoreSharedManager sharedInstance] printDeviceToken];
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
  [[NetCorePushTaskManager sharedInstance] didReceiveRemoteNotification:userInfo];
}


- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
{
  return true;
}
- (void)handleNotificationOpenAction:(NSDictionary *)userInfo DeepLinkType:(NSString *)strType
{
  
}
- (void)handleNotificationCustomPayload:(NSDictionary *)payload
{
  
}
@end
