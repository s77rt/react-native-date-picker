#import <React/RCTViewManager.h>

@interface RTNDatePickerManager : RCTViewManager
@end

@implementation RTNDatePickerManager

RCT_EXPORT_MODULE(RTNDatePicker)

RCT_EXPORT_VIEW_PROPERTY(isOpen, BOOL)

@end
