//
//  DeeplinkManager.m
//  ReactDemoProject
//
//  Created by NetcoreSoltion on 14/05/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "DeeplinkManager.h"

@implementation DeeplinkManager
RCT_EXPORT_MODULE();


- (void)startObserving
{
  [[NSNotificationCenter defaultCenter] addObserver:self
                                           selector:@selector(sendDeeplink:)
                                               name:@"SMTDeeplink"
                                             object:nil];
}

- (void)stopObserving
{
  [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)sendDeeplink:(NSNotification *)notifcation {
  NSDictionary<NSString *, id> *payload = @{@"deeplink": notifcation.userInfo[@"deeplink"],
                                            @"customPayload":notifcation.userInfo[@"customPayload"],
                                            @"userInfo":notifcation.userInfo[@"userInfo"]};
  [self sendEventWithName:@"SMTDeeplink" body:payload];
}

//This deeplink method is used for v2
+ (void)emitEventWithDeeplink:(NSString *)deeplink customPayload:(NSDictionary *)customPayload andPayload:(NSDictionary *)payload {
  NSString *deeplinkString = deeplink ? deeplink : @"";
  NSDictionary *customPayloadDictionary = customPayload ? customPayload : @{};
  NSDictionary *payloadDictionary = payload ? payload : @{};
  
  NSDictionary<NSString *, id> *userInfo = @{@"deeplink": deeplinkString,
                                             @"customPayload": customPayloadDictionary,
                                             @"userInfo":payloadDictionary};
  [[NSNotificationCenter defaultCenter] postNotificationName:@"SMTDeeplink"
                                                      object:self
                                                    userInfo:userInfo];
}

//This deeplink method is used for v2.5
+ (void)emitEventWithDeeplink:(NSString *)deeplink customPayload:(NSDictionary *)customPayload {
  NSString *deeplinkString = deeplink ? deeplink : @"";
  NSDictionary *customPayloadDictionary = customPayload ? customPayload : @{};
  NSDictionary *payloadDictionary = @{};
  
  NSDictionary<NSString *, id> *userInfo = @{@"deeplink": deeplinkString,
                                             @"customPayload": customPayloadDictionary,
                                             @"userInfo":payloadDictionary};
  [[NSNotificationCenter defaultCenter] postNotificationName:@"SMTDeeplink"
                                                      object:self
                                                    userInfo:userInfo];
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"SMTDeeplink"];
}

RCT_EXPORT_METHOD(getInitialDeeplink:(RCTPromiseResolveBlock)resolve
                  reject:(__unused RCTPromiseRejectBlock)reject)
{
  NSURL *initialURL = nil;
  if (self.bridge.launchOptions[UIApplicationLaunchOptionsURLKey]) {
    initialURL = self.bridge.launchOptions[UIApplicationLaunchOptionsURLKey];
  } else {
    NSDictionary *userActivityDictionary =
    self.bridge.launchOptions[UIApplicationLaunchOptionsUserActivityDictionaryKey];
    if ([userActivityDictionary[UIApplicationLaunchOptionsUserActivityTypeKey] isEqual:NSUserActivityTypeBrowsingWeb]) {
      initialURL = ((NSUserActivity *)userActivityDictionary[@"UIApplicationLaunchOptionsUserActivityKey"]).webpageURL;
    }
  }
}


@end
