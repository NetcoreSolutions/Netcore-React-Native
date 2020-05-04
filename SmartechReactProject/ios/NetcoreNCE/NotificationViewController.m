//
//  NotificationViewController.m
//  NetcoreNCE
//
//  Created by jainish on 24/10/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "NotificationViewController.h"
#import <UserNotifications/UserNotifications.h>
#import <UserNotificationsUI/UserNotificationsUI.h>
#import <NetCorePush/NetCorePush.h>

@interface NotificationViewController () <UNNotificationContentExtension>

@property (weak, nonatomic) IBOutlet UIView *customBgView;

@end

@implementation NotificationViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any required interface initialization here.
  [[Smartech sharedInstance] loadCustomNotificationContentView:_customBgView];
}

- (void)didReceiveNotification:(UNNotification *)notification {
  [[Smartech sharedInstance] didReceiveCustomNotification:notification];
}

-(void)didReceiveNotificationResponse:(UNNotificationResponse *)response completionHandler:(void (^)(UNNotificationContentExtensionResponseOption))completion {
  [[Smartech sharedInstance] didReceiveCustomNotificationResponse:response completionHandler:^(UNNotificationContentExtensionResponseOption option) {
    completion(option);
  }];
}

@end
