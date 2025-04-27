#import "RTNDatePicker.h"

#import <react/renderer/components/RTNDatePickerSpecs/ComponentDescriptors.h>
#import <react/renderer/components/RTNDatePickerSpecs/EventEmitters.h>
#import <react/renderer/components/RTNDatePickerSpecs/Props.h>
#import <react/renderer/components/RTNDatePickerSpecs/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"

using namespace facebook::react;

@interface RTNDatePicker () <RCTRTNDatePickerViewProtocol>
@end

@implementation RTNDatePicker {
  UIView *_view;
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

    _view = [[UIView alloc] init];
    _view.backgroundColor = [UIColor redColor];

    self.contentView = _view;
  }

  return self;
}

@end

Class<RCTComponentViewProtocol> RTNDatePickerCls(void) {
  return RTNDatePicker.class;
}