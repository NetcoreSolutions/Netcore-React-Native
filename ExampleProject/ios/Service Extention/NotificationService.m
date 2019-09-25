//
//  NotificationService.m
//  Service Extention
//
//  Created by Vikran Barai on 27/02/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "NotificationService.h"
#import <NetCorePush/NetCorePush.h>

@interface NotificationService ()

@property (nonatomic, strong) void (^contentHandler)(UNNotificationContent *contentToDeliver);
@property (nonatomic, strong) UNMutableNotificationContent *bestAttemptContent;

@end

@implementation NotificationService

- (void)didReceiveNotificationRequest:(UNNotificationRequest *)request withContentHandler:(void (^)(UNNotificationContent * _Nonnull))contentHandler {
  [[NetCoreNotificationService sharedInstance] setUpAppGroup:@"<APP_GROUP>"];
  [[NetCoreNotificationService sharedInstance] didReceiveNotificationRequest:request withContentHandler:^(UNNotificationContent *contentToDeliver) {
    contentHandler(contentToDeliver);
  }];
}

- (void)serviceExtensionTimeWillExpire {
    // Called just before the extension will be terminated by the system.
    // Use this as an opportunity to deliver your "best attempt" at modified content, otherwise the original push payload will be used.
    self.contentHandler(self.bestAttemptContent);
}

@end
