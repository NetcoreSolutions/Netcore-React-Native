/*
 @header NetCoreInstallation.h
 
 @brief NetCoreInstallation : -use to setup all network communication methods
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.9 */
#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import "NetCoreConstant.h"

@interface NetCoreInstallation : NSObject

/*
 @Method sharedInstance:- use to return NetCoreSharedManager manage class Object
 @return NetCoreSharedManager instance
 */
+(instancetype)sharedInstance;
/*
 netCorePushRegisteration return response block
 */
-(void)netCorePushRegisteration:(NSString *)strIdentity Block:(NetCoreStatusBlock)block;
/*
 netCorePushLogin taking input as Identity and return response block
 */
-(void)netCorePushLogin:(NSString *)strIdentity Block:(NetCoreStatusBlock)block;
/*
 netCorePushLogout taking input as Identity and return response block
 */
-(void)netCorePushLogout:(NetCoreStatusBlock)block;
/*
 netCoreProfilePush taking input as Identity and custom payload and return response block
 */
-(void)netCoreProfilePush:(NSString *)strIdentity Payload:(NSDictionary *)payload Block:(NetCoreStatusBlock)block;
/*
 netCoreProfilePush taking input as Identity and custom payload and devicetoken and return response block
 */
-(void)netCorePushRegisteration:(NSString *)strIdentity withDeviceToken:(NSData *)deviceToken Block:(NetCoreStatusBlock)block;

@end
