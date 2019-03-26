
#import "SMTSmartechReactNative.h"
#import <NetCorePush/NetCorePush.h>


@implementation SMTSmartechReactNative

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

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

@end

