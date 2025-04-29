#import <React/RCTViewComponentView.h>

#import <react/renderer/components/RTNDatePickerSpecs/ComponentDescriptors.h>
#import <react/renderer/components/RTNDatePickerSpecs/EventEmitters.h>
#import <react/renderer/components/RTNDatePickerSpecs/Props.h>
#import <react/renderer/components/RTNDatePickerSpecs/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"

#import "react_native_date_picker-Swift.h"

using namespace facebook::react;

@interface RTNDatePicker : RCTViewComponentView
@end

@interface RTNDatePicker () <RCTRTNDatePickerViewProtocol>
@end

@implementation RTNDatePicker {
  RTNDatePickerUIView *_view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider {
  return concreteComponentDescriptorProvider<
      RTNDatePickerComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps =
        std::make_shared<const RTNDatePickerProps>();
    _props = defaultProps;

    _view = [[RTNDatePickerUIView alloc] initWithFrame:frame];

    self.contentView = _view;
  }

  return self;
}

@end

Class<RCTComponentViewProtocol> RTNDatePickerCls(void) {
  return RTNDatePicker.class;
}
