/*
 @header NetCoreConstant.h
 
 @brief NetCoreConstant : - contains the networking block short name
 
 @author Netcore Solutions
 @copyright  2019 Netcore Solutions
 @version    2.3.9 */

#ifndef NetCoreConstant_h
#define NetCoreConstant_h

typedef void (^NetCoreStatusBlock)(NSInteger statusCode);
typedef void (^NetCoreResultBlock)(NSInteger statusCode,id result);

#define SYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedAscending)

#endif /* NetCoreConstant_h */
