//
//  deeplinkManager.h
//  ReactDemoProject
//
//  Created by Vikran Barai on 14/05/19.
//  Copyright © 2019 Facebook. All rights reserved.
//



#if __has_include("RCTEventEmitter.h")
#import "RCTEventEmitter.h"
#else
#import <React/RCTEventEmitter.h>
#endif


NS_ASSUME_NONNULL_BEGIN

@interface DeeplinkManager : RCTEventEmitter <RCTBridgeModule>

/**
@brief This method is used to emit deeplink data to js code for sdk v2

@param deeplink - deeplink string in notification.
@param customPayload - custom payload dictionary in notification.
@param payload - notification payload.
*/
+ (void)emitEventWithDeeplink:(NSString *)deeplink customPayload:(NSDictionary *)customPayload andPayload:(NSDictionary *)payload;

/**
@brief This method is used to emit deeplink data to js code for sdk v2.5

@param deeplink - deeplink string in notification.
@param customPayload - custom payload dictionary in notification.
*/
+ (void)emitEventWithDeeplink:(NSString *)deeplink customPayload:(NSDictionary *)customPayload;

@end

NS_ASSUME_NONNULL_END
