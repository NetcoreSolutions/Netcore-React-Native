//
//  NotificationService.m
//  NetcoreNSE
//
//  Created by jainish on 24/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "NotificationService.h"
#import <NetCorePush/NetCorePush.h>

@interface NotificationService ()

@end

@implementation NotificationService

- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request withContentHandler:(void (^)(UNNotificationContent * _Nonnull))contentHandler {
  [[NetCoreNotificationService sharedInstance] setUpAppGroup:@"group.com.netcore.NetcoreApp"];
  [[NetCoreNotificationService sharedInstance] didReceiveNotificationRequest:request withContentHandler:^(UNNotificationContent *contentToDeliver) {
    contentHandler(contentToDeliver);
  }];
}

- (void)serviceExtensionTimeWillExpire {
  [[NetCoreNotificationService sharedInstance] serviceExtensionTimeWillExpire];
}

@end
