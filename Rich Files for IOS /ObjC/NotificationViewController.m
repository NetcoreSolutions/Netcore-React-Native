//
//  NotificationViewController.m
//  Content Extention
//
//  Created by Admin on 29/03/18.
//  Copyright © 2018 Manish Kumar. All rights reserved.
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
    
    [[NetCoreNotificationService sharedInstance] contentViewDidLoad:_customBgView];
}

- (void)didReceiveNotification:(UNNotification *)notification {
    
    [[NetCoreNotificationService sharedInstance] didReceiveNotification:notification];
}

-(void)didReceiveNotificationResponse:(UNNotificationResponse *)response completionHandler:(void (^)(UNNotificationContentExtensionResponseOption))completion{
    [[NetCoreNotificationService sharedInstance] didReceiveNotificationResponse:response completionHandler:(void (^)(UNNotificationContentExtensionResponseOption))completion];
    
}

@end
