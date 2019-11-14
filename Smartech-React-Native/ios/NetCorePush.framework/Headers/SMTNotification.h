/*
 @header SMTNotification.h
 
 @brief This is the header file for SMTNotification model used in Notification Centre
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.9 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#define readMoreButtonHeightConstant 22.0
#define kInteractionButtonView 40.0
#define kTextLimit 176

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSUInteger,SMTMediaType) {
    SMTMediaTypeText,
    SMTMediaTypeImage,
    SMTMediaTypeVideo,
    SMTMediaTypeAudio,
    SMTMediaTypeGif,
    SMTMediaTypeCarousel,
};

@class SMTCarousel,SMTActionButton;

@interface SMTNotification : NSObject

@property (strong, nonatomic) NSDictionary *userInfo;
@property (strong, nonatomic) UIImage *image;
@property (strong, nonatomic) NSString *dateString;
@property (strong, nonatomic) NSString *titleString;
@property (strong, nonatomic) NSString *subtitleString;
@property (strong, nonatomic) NSString *deeplinkString;
@property (strong, nonatomic) NSString *mediaurl;
@property (strong, nonatomic) NSString *trid;
@property (nonatomic) int mediaType;
//used to check that the is the cell expandable or not
@property (nonatomic) Boolean isCellExpandable;
//used to check that the expanded state of cell
@property (nonatomic) Boolean isCellExpanded;
//used to check that the read status of notification
@property (nonatomic) Boolean isNotificationRead;
@property (strong, nonatomic) NSDictionary *customPayload;
@property NSArray <SMTCarousel *> *carouselArray;
@property NSArray <SMTActionButton *> *actionButtonArray;

- (instancetype)initWithNotification:(NSArray *)dataArray;

+ (NSArray <SMTNotification *>*)getNotifications;

+ (NSArray <SMTNotification *>*)getNotificationsWithCount:(NSInteger )count;

+ (NSUInteger)getUnreadNotificationsCount;

+ (void)markNotificationAsRead:(NSDictionary *)messageDict autoHandleDeeplink:(BOOL)shouldHandle;

+ (BOOL)deleteNotification:(NSArray *)notificationIDs;

@end



@interface SMTDateFormatter : NSObject

+(instancetype)sharedInstance;

@property (strong, nonatomic) NSDateFormatter *dateFormatter;
-(NSDateFormatter *)dateFormatterInitializer;

@end



@interface SMTCarousel : NSObject

@property (strong, nonatomic) NSString *imageUrlString;
@property (strong, nonatomic) NSString *imageTitleString;
@property (strong, nonatomic) NSString *imageMessageString;


- (instancetype)initWithCarousel:(NSDictionary *)data;

@end



@interface SMTActionButton : NSObject

@property (strong, nonatomic) NSString *deeplinkString;
@property (strong, nonatomic) NSString *titleString;

- (instancetype)initWithActionButton:(NSDictionary *)data;

@end

NS_ASSUME_NONNULL_END
