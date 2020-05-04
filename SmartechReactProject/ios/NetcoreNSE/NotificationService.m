//
//  NotificationService.m
//  NetcoreNSE
//
//  Created by jainish on 24/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "NotificationService.h"

@interface NotificationService ()

@end

@implementation NotificationService

- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request withContentHandler:(void (^)(UNNotificationContent * _Nonnull))contentHandler {
  [super didReceiveNotificationRequest:request withContentHandler:contentHandler];
}

- (void)serviceExtensionTimeWillExpire {
  [super serviceExtensionTimeWillExpire];
}

@end
