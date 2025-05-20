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

    {
      [_view setTypeWithType:[NSString stringWithUTF8String:defaultViewProps
                                                                .type.c_str()]];
    }

    { [_view setIsOpenWithIsOpen:defaultViewProps.isOpen]; }

    { [_view setIsInlineWithIsInline:defaultViewProps.isInline]; }

    {
      [_view setValueWithDate:[NSDate
                                  dateWithTimeIntervalSince1970:defaultViewProps
                                                                    .value]];
    }

    {
      // Codegen props of type double are zero-initialized (undefined values are
      // zeroed by default)
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
    }

    {
      // Codegen props of type string are zero-initialized (undefined values are
      // empty by default)
      NSString *confirmText =
          defaultViewProps.options.confirmText.empty()
              ? nil
              : [NSString stringWithUTF8String:defaultViewProps.options
                                                   .confirmText.c_str()];
      [_view setConfirmTextWithConfirmText:confirmText];

      NSString *cancelText =
          defaultViewProps.options.cancelText.empty()
              ? nil
              : [NSString stringWithUTF8String:defaultViewProps.options
                                                   .cancelText.c_str()];
      [_view setCancelTextWithCancelText:cancelText];

      [_view setModeWithMode:[NSString
                                 stringWithUTF8String:defaultViewProps.options
                                                          .mode.c_str()]];
    }

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

  if (oldViewProps.type != newViewProps.type) {
    [_view setTypeWithType:[NSString
                               stringWithUTF8String:newViewProps.type.c_str()]];
  }

  if (oldViewProps.isOpen != newViewProps.isOpen) {
    [_view setIsOpenWithIsOpen:newViewProps.isOpen];
  }

  if (oldViewProps.isInline != newViewProps.isInline) {
    [_view setIsInlineWithIsInline:newViewProps.isInline];
  }

  if (oldViewProps.value != newViewProps.value) {
    [_view
        setValueWithDate:[NSDate
                             dateWithTimeIntervalSince1970:newViewProps.value]];
  }

  if (oldViewProps.range.lowerBound != newViewProps.range.lowerBound ||
      oldViewProps.range.upperBound != newViewProps.range.upperBound) {
    // Codegen props of type double are zero-initialized (undefined values are
    // zeroed by default)
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

  if (oldViewProps.options.confirmText != newViewProps.options.confirmText ||
      oldViewProps.options.cancelText != newViewProps.options.cancelText ||
      oldViewProps.options.mode != newViewProps.options.mode) {
    // Codegen props of type string are zero-initialized (undefined values are
    // empty by default)
    NSString *confirmText =
        newViewProps.options.confirmText.empty()
            ? nil
            : [NSString stringWithUTF8String:newViewProps.options.confirmText
                                                 .c_str()];
    [_view setConfirmTextWithConfirmText:confirmText];

    NSString *cancelText =
        newViewProps.options.cancelText.empty()
            ? nil
            : [NSString
                  stringWithUTF8String:newViewProps.options.cancelText.c_str()];
    [_view setCancelTextWithCancelText:cancelText];

    [_view setModeWithMode:[NSString stringWithUTF8String:newViewProps.options
                                                              .mode.c_str()]];
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
