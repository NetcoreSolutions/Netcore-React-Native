
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

RCT_EXPORT_METHOD(getBoxxReco:(NSDictionary *)boxxRequestDictionary
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[Smartech sharedInstance] getReco:[self createBoxxModel:boxxRequestDictionary] withCompletionBlock:^(NSDictionary * _Nullable responseData) {
            resolve(responseData);
        }];
    } @catch (NSException *exception) {
        reject(@"Error while calling Default Boxx API", exception.description, nil);
    }
}

RCT_EXPORT_METHOD(getBoxxBestSellerReco:(NSDictionary *)boxxRequestDictionary
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[Smartech sharedInstance] getBestSellerReco:[self createBoxxModel:boxxRequestDictionary] withCompletionBlock:^(NSDictionary * _Nullable responseData) {
            resolve(responseData);
        }];
    } @catch (NSException *exception) {
        reject(@"Error while calling Boxx Best Seller API", exception.description, nil);
    }
}

RCT_EXPORT_METHOD(getBoxxTrendingReco:(NSDictionary *)boxxRequestDictionary
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[Smartech sharedInstance] getTrendingReco:[self createBoxxModel:boxxRequestDictionary] withCompletionBlock:^(NSDictionary * _Nullable responseData) {
            resolve(responseData);
        }];
    } @catch (NSException *exception) {
        reject(@"Error while calling Boxx Trending API", exception.description, nil);
    }
}

RCT_EXPORT_METHOD(getBoxxRecentlyViewedReco:(NSDictionary *)boxxRequestDictionary
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[Smartech sharedInstance] getRecentlyViewedReco:[self createBoxxModel:boxxRequestDictionary] withCompletionBlock:^(NSDictionary * _Nullable responseData) {
            resolve(responseData);
        }];
    } @catch (NSException *exception) {
        reject(@"Error while calling Boxx Recently Viewed API", exception.description, nil);
    }
}

RCT_EXPORT_METHOD(getBoxxNewProductsReco:(NSDictionary *)boxxRequestDictionary
                  resolver:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[Smartech sharedInstance] getNewProductsReco:[self createBoxxModel:boxxRequestDictionary] withCompletionBlock:^(NSDictionary * _Nullable responseData) {
            resolve(responseData);
        }];
    } @catch (NSException *exception) {
        reject(@"Error while calling Boxx New Products API", exception.description, nil);
    }
}

- (SMTBoxxRequest *)createBoxxModel:(NSDictionary *)boxxRequestDictionary {
    SMTBoxxRequest *boxxRequest = [SMTBoxxRequest new];
    
    @try {
        boxxRequest.locale = boxxRequestDictionary[@"locale"];
        
        NSMutableDictionary *boxxRequestQueryDictionary = boxxRequestDictionary[@"query"];
        
        if (boxxRequestQueryDictionary != nil && boxxRequestQueryDictionary.allKeys.count > 0) {
            
            SMTBoxxRequestQuery *boxxRequestQuery = [SMTBoxxRequestQuery new];
            
            if (boxxRequestQueryDictionary[@"related_action_type"]) {
                boxxRequestQuery.relatedActionType = boxxRequestQueryDictionary[@"related_action_type"];
                [boxxRequestQueryDictionary removeObjectForKey:@"related_action_type"];
            }
            
            if (boxxRequestQueryDictionary[@"related_products"]) {
                boxxRequestQuery.relatedProducts = boxxRequestQueryDictionary[@"related_products"];
                [boxxRequestQueryDictionary removeObjectForKey:@"related_products"];
            }
            
            if (boxxRequestQueryDictionary[@"exclude"]) {
                boxxRequestQuery.exclude = boxxRequestQueryDictionary[@"exclude"];
                [boxxRequestQueryDictionary removeObjectForKey:@"exclude"];
            }
            
            if (boxxRequestQueryDictionary[@"dont_repeat_transaction_types"]) {
                boxxRequestQuery.dontRepeatTransactionTypes = boxxRequestQueryDictionary[@"dont_repeat_transaction_types"];
                [boxxRequestQueryDictionary removeObjectForKey:@"dont_repeat_transaction_types"];
            }
            
            if (boxxRequestQueryDictionary[@"get_product_properties"]) {
                boxxRequestQuery.getProductProperties = [boxxRequestQueryDictionary[@"get_product_properties"] boolValue];
                [boxxRequestQueryDictionary removeObjectForKey:@"get_product_properties"];
            }
            
            if (boxxRequestQueryDictionary[@"get_product_liked_disliked_status"]) {
                boxxRequestQuery.getProductLikedDislikedStatus = [boxxRequestQueryDictionary[@"get_product_liked_disliked_status"] boolValue];
                [boxxRequestQueryDictionary removeObjectForKey:@"get_product_liked_disliked_status"];
            }
            
            if (boxxRequestQueryDictionary[@"get_product_aliases"]) {
                boxxRequestQuery.getProductAliases = [boxxRequestQueryDictionary[@"get_product_aliases"] boolValue];
                [boxxRequestQueryDictionary removeObjectForKey:@"get_product_aliases"];
            }
            
            if (boxxRequestQueryDictionary[@"num"]) {
                boxxRequestQuery.num = [boxxRequestQueryDictionary[@"num"] integerValue];
                [boxxRequestQueryDictionary removeObjectForKey:@"num"];
            }
            
            if (boxxRequestQueryDictionary[@"offset"]) {
                boxxRequestQuery.offset = [boxxRequestQueryDictionary[@"offset"] integerValue];
                [boxxRequestQueryDictionary removeObjectForKey:@"offset"];
            }
            
            if (boxxRequestQueryDictionary[@"item_filters"]) {
                boxxRequestQuery.itemFilters = boxxRequestQueryDictionary[@"item_filters"];
                [boxxRequestQueryDictionary removeObjectForKey:@"item_filters"];
            }
            
            if (boxxRequestQueryDictionary[@"context"]) {
                boxxRequestQuery.context = boxxRequestQueryDictionary[@"context"];
                [boxxRequestQueryDictionary removeObjectForKey:@"context"];
            }
            
            if (boxxRequestQueryDictionary != nil && boxxRequestQueryDictionary.allKeys.count > 0) {
                
                NSMutableDictionary *dictionary = [NSMutableDictionary new];
                
                for (NSString *key in boxxRequestQueryDictionary.allKeys) {
                    dictionary[key] = boxxRequestQueryDictionary[key];
                }
                
                boxxRequestQuery.extras = dictionary;
            }
            
            boxxRequest.requestQuery = boxxRequestQuery;
            
        }
        return boxxRequest;
    } @catch (NSException *exception) {
        NSLog(@"Error while creating Boxx Model: %@", exception.description);
    } @finally {
        return boxxRequest;
    }
}

@end
