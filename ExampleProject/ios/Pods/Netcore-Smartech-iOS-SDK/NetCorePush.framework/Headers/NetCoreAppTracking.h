 /*
 @header NetCoreAppTracking.h
 
 @brief NetCoreAppTracking : - use to setup all event communication methods
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.8 */
#import <Foundation/Foundation.h>
#import "NetCoreConstant.h"

@interface NetCoreAppTracking : NSObject
/*
 @Method sharedInstance:- use to return NetCoreAppTracking manage class Object
 @return NetCoreAppTracking instance
 */
+ (instancetype)sharedInstance;

/*
  sendAppTrackingEvent takes event and return response block
 */
-(void)sendAppTrackingEvent:(NSInteger)event Block:(NetCoreStatusBlock)block;

/*
 sendAppTrackingEventWithCustomPayload takes event and custom payload and return response block
 */
-(void)sendAppTrackingEventWithCustomPayload:(NSInteger)event Payload:(NSMutableArray *)payload Block:(NetCoreStatusBlock)block;
/*
 use to send App Tracking Event name
 */
-(void)trackEvent:(NSString *)eventName Block:(NetCoreStatusBlock)block;
/*
 use to send app tracking Event with payload
 */
-(void)trackEventWithCustomPayload:(NSString *)eventName Payload:(NSMutableDictionary *)payloadDict Block:(NetCoreStatusBlock)block;

@end
