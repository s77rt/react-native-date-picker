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

@interface RTNDatePicker () <RCTRTNDatePickerViewProtocol,
                             RTNDatePickerUIViewDelegate>
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

    const auto &defaultViewProps =
        *std::static_pointer_cast<RTNDatePickerProps const>(_props);

    _view = [[RTNDatePickerUIView alloc] initWithFrame:frame];
    _view.delegate = self;

    [_view setIsOpenWithIsOpen:defaultViewProps.isOpen];

    [_view
        setValueWithDate:[NSDate dateWithTimeIntervalSince1970:defaultViewProps
                                                                   .value]];

    // Codegen props are zero-initialized (undefined values are zeroed by
    // default)
    NSDate *lowerBound =
        defaultViewProps.range.lowerBound == 0.0
            ? NSDate.distantPast
            : [NSDate dateWithTimeIntervalSince1970:defaultViewProps.range
                                                        .lowerBound];
    NSDate *upperBound =
        defaultViewProps.range.upperBound == 0.0
            ? NSDate.distantFuture
            : [NSDate dateWithTimeIntervalSince1970:defaultViewProps.range
                                                        .upperBound];
    [_view setRangeWithLowerBound:lowerBound upperBound:upperBound];

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props
           oldProps:(Props::Shared const &)oldProps {
  const auto &oldViewProps =
      *std::static_pointer_cast<RTNDatePickerProps const>(_props);
  const auto &newViewProps =
      *std::static_pointer_cast<RTNDatePickerProps const>(props);

  if (oldViewProps.isOpen != newViewProps.isOpen) {
    [_view setIsOpenWithIsOpen:newViewProps.isOpen];
  }

  if (oldViewProps.value != newViewProps.value) {
    [_view
        setValueWithDate:[NSDate
                             dateWithTimeIntervalSince1970:newViewProps.value]];
  }

  if (oldViewProps.range.lowerBound != newViewProps.range.lowerBound ||
      oldViewProps.range.upperBound != newViewProps.range.upperBound) {
    // Codegen props are zero-initialized (undefined values are zeroed by
    // default)
    NSDate *lowerBound =
        newViewProps.range.lowerBound == 0.0
            ? NSDate.distantPast
            : [NSDate
                  dateWithTimeIntervalSince1970:newViewProps.range.lowerBound];
    NSDate *upperBound =
        newViewProps.range.upperBound == 0.0
            ? NSDate.distantFuture
            : [NSDate
                  dateWithTimeIntervalSince1970:newViewProps.range.upperBound];
    [_view setRangeWithLowerBound:lowerBound upperBound:upperBound];
  }

  [super updateProps:props oldProps:oldProps];
}

- (void)onChangeWithDate:(NSDate *_Nonnull)date {
  if (_eventEmitter) {
    static_cast<const RTNDatePickerEventEmitter &>(*_eventEmitter)
        .onChange({date.timeIntervalSince1970});
  }
}

- (void)onConfirm {
  if (_eventEmitter) {
    static_cast<const RTNDatePickerEventEmitter &>(*_eventEmitter)
        .onConfirm({});
  }
}

- (void)onCancel {
  if (_eventEmitter) {
    static_cast<const RTNDatePickerEventEmitter &>(*_eventEmitter).onCancel({});
  }
}

@end

Class<RCTComponentViewProtocol> RTNDatePickerCls(void) {
  return RTNDatePicker.class;
}
