#import <React/RCTViewManager.h>

@interface RTNDatePickerManager : RCTViewManager
@end

@implementation RTNDatePickerManager

RCT_EXPORT_MODULE(RTNDatePicker)

RCT_EXPORT_VIEW_PROPERTY(type, NSString)
RCT_EXPORT_VIEW_PROPERTY(isOpen, BOOL)
RCT_EXPORT_VIEW_PROPERTY(isInline, BOOL)
RCT_EXPORT_VIEW_PROPERTY(value, double)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onConfirm, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onCancel, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(range, RTNDatePickerRangeStruct)
RCT_EXPORT_VIEW_PROPERTY(options, RTNDatePickerOptionsStruct)

@end
