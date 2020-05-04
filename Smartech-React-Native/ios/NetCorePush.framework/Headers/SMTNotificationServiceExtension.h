//
//  SMTNotificationServiceExtension.h
//  NetCorePush
//
//  Created by jainish on 21/03/20.
//  Copyright © 2020 NetCore. All rights reserved.
//

#import <UserNotifications/UserNotifications.h>

NS_ASSUME_NONNULL_BEGIN

API_AVAILABLE(ios(10.0))
@interface SMTNotificationServiceExtension : UNNotificationServiceExtension

/**
 @brief This method is useful for modifying the notification content before it is delivered.
 
 @param request - the notification request to be handled.
 @param contentHandler - Call contentHandler with the modified notification content to deliver. If the handler is not called before the service's time expires then the unmodified notification will be delivered.
 */
- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request
                   withContentHandler:(void (^)(UNNotificationContent *contentToDeliver))contentHandler API_AVAILABLE(ios(10.0));

/**
 @brief This method will be called just before this extension is terminated by the system. You may choose whether to override this method.
 */
- (void)serviceExtensionTimeWillExpire;

@end

NS_ASSUME_NONNULL_END
