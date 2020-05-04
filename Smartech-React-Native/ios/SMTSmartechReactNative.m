
#import "SMTSmartechReactNative.h"
#import <NetCorePush/NetCorePush.h>


@implementation SMTSmartechReactNative

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

#pragma mark - v2 methods
RCT_EXPORT_METHOD(setIdentity:(NSString *) identity
                  identityResolver:(RCTPromiseResolveBlock)resolve
                  identityRejecter:(RCTPromiseRejectBlock)reject)
{
    [[NetCoreSharedManager sharedInstance]setUpIdentity:identity];
    resolve(@TRUE);
}

RCT_REMAP_METHOD(clearIdentity,
                  clearIdentityResolver:(RCTPromiseResolveBlock)resolve
                  clearIdentityRejecter:(RCTPromiseRejectBlock)reject) {
    [[NetCoreSharedManager sharedInstance] clearIdentity];
    resolve(@TRUE);
}

RCT_EXPORT_METHOD(login:(NSString *)loginID
                  loginResolver:(RCTPromiseResolveBlock)resolve
                  loginRejecter:(RCTPromiseRejectBlock)reject) {

    [[NetCoreInstallation sharedInstance] netCorePushLogin:loginID Block:^(NSInteger statusCode) {
        if(statusCode == 200) {
            resolve(@TRUE);
        } else {
            NSError *error = [[NSError alloc] initWithDomain:@"Error" code:statusCode userInfo:nil];
            reject(@"ERROR",@"Something went wrong",error);
        }
    }];
}

RCT_EXPORT_METHOD(setUserLocation:(nonnull NSNumber *)latitude:(nonnull NSNumber *)longitude) {
    NSLog(@"this functionality is not for iOS");
}


RCT_EXPORT_METHOD(track:(NSString *)eventName
                  andValue:(NSDictionary  *)eventValue) {
    [[NetCoreAppTracking sharedInstance] trackEventWithCustomPayload:eventName Payload:(NSMutableDictionary *)eventValue  Block:^(NSInteger statusCode) {
    }];
}

RCT_REMAP_METHOD(logout,
                  logoutResolver:(RCTPromiseResolveBlock)resolve
                  logoutRejecter:(RCTPromiseRejectBlock)reject) {
    [[NetCoreInstallation sharedInstance] netCorePushLogout:^(NSInteger statusCode) {
        if(statusCode == 200) {
            resolve(@TRUE);
        } else {
            NSError *error = [[NSError alloc] initWithDomain:@"Error" code:statusCode userInfo:nil];
            reject(@"ERROR",@"Something went wrong",error);
        }
    }];
}

RCT_EXPORT_METHOD(profile:(NSDictionary *)profileDetails
                  profileResolver:(RCTPromiseResolveBlock)resolve
                  profileRejecter:(RCTPromiseRejectBlock)reject) {
    
    [[NetCoreInstallation sharedInstance]netCoreProfilePush:[[NetCoreSharedManager sharedInstance]getIdentity] Payload:(NSMutableDictionary *)profileDetails Block:^(NSInteger statusCode) {
        if(statusCode == 200) {
            resolve(@TRUE);
        } else {
            NSError *error = [[NSError alloc] initWithDomain:@"Error" code:statusCode userInfo:nil];
            reject(@"ERROR",@"Something went wrong",error);
        }
    }];

}



RCT_EXPORT_METHOD(optOut:(BOOL )optOutFlag
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    [[NetCoreSharedManager sharedInstance] optOut:optOutFlag];
    resolve(@TRUE);
}


// ios specific method, todo change it later
RCT_EXPORT_METHOD(getNotifications:(RCTResponseSenderBlock)callback){
    callback(@[[NSNull null], [NSString stringWithFormat:@"%@",[[NetCoreSharedManager sharedInstance
                                                                 ]getNotifications]]]);
}


RCT_REMAP_METHOD(getPushToken,
                  pushTokenResolver:(RCTPromiseResolveBlock)resolve
                  pushTokenRejecter:(RCTPromiseRejectBlock)reject){

    NSString *token = [[NetCoreSharedManager sharedInstance]getDeviceToken];
    resolve(token);
}

RCT_REMAP_METHOD(getGUID,
                  guidResolver:(RCTPromiseResolveBlock)resolve
                  guidRejecter:(RCTPromiseRejectBlock)reject) {
    NSString *guid = [[NetCoreSharedManager sharedInstance]getGUID];
    resolve(guid);
}

RCT_EXPORT_METHOD(setPushToken:(NSString *)pushToken) {
    NSLog(@"this functionality is not for iOS");
}

#pragma mark - v2.5.0 methods
RCT_EXPORT_METHOD(setUserIdentity:(NSString *)identity) {
    [[Smartech sharedInstance] setUserIdentity:identity];
}

RCT_EXPORT_METHOD(clearUserIdentity) {
    [[Smartech sharedInstance] clearUserIdentity];
}

RCT_REMAP_METHOD(getUserIdentity, userIdentityResolver:(RCTPromiseResolveBlock)resolve userIdentityRejecter:(RCTPromiseRejectBlock)reject) {
    
    NSString *userIdentity = [[Smartech sharedInstance] getUserIdentity];
    resolve(userIdentity);
}

RCT_EXPORT_METHOD(login:(NSString *)identity) {
    [[Smartech sharedInstance] login:identity];
}

RCT_EXPORT_METHOD(logoutAndClearUserIdentity:(BOOL)clearUserIdentity) {
    [[Smartech sharedInstance] logoutAndClearUserIdentity:clearUserIdentity];
}

RCT_EXPORT_METHOD(updateUserProfile:(NSDictionary *)profileDetails) {
    [[Smartech sharedInstance] updateUserProfile:profileDetails];
}

RCT_EXPORT_METHOD(trackEvent:(NSString *)eventName andValue:(NSDictionary *)eventValue) {
    [[Smartech sharedInstance] trackEvent:eventName andPayload:eventValue];
}

RCT_REMAP_METHOD(getDevicePushToken, getDevicePushTokenResolver:(RCTPromiseResolveBlock)resolve getDevicePushTokenRejecter:(RCTPromiseRejectBlock)reject) {
    
    NSString *token = [[Smartech sharedInstance] getDevicePushToken];
    resolve(token);
}

RCT_REMAP_METHOD(getOptOutStatus, optStatusResolver:(RCTPromiseResolveBlock)resolve optStatusRejecter:(RCTPromiseRejectBlock)reject) {
    
    BOOL optOutStatus = [[Smartech sharedInstance] getOptOutStatus];
    resolve(@(optOutStatus));
}

RCT_EXPORT_METHOD(setOptOutStatus:(BOOL)optOutFlag) {
    [[Smartech sharedInstance] setOptOutStatus:optOutFlag];
}

RCT_REMAP_METHOD(getDeviceUniqueId, deviceUniqueIdResolver:(RCTPromiseResolveBlock)resolve deviceUniqueIdRejecter:(RCTPromiseRejectBlock)reject) {
    
    NSString *guid = [[Smartech sharedInstance] getDeviceGuid];
    resolve(guid);
}

RCT_EXPORT_METHOD(trackNotificationOpenEvent:(NSDictionary *)userInfo shouldHandleDeeplink:(BOOL)shouldHandle withDeeplink:(NSString *)deeplink) {
    
    [[Smartech sharedInstance] trackNotificationOpenEvent:userInfo shouldHandleDeeplink:shouldHandle withDeeplink:deeplink];
}

@end
