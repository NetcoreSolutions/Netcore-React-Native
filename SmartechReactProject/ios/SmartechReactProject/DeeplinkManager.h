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

+ (void)emitEventWithDeeplink:(NSString *)deeplink customPayload:(NSDictionary *)customPayload andPayload:(NSDictionary *)payload;

@end

NS_ASSUME_NONNULL_END
