#import "RTNDatePicker.h"

#import "RTNDatePickerComponentDescriptor.h"

#import <react/renderer/components/RTNDatePickerSpecs/EventEmitters.h>
#import <react/renderer/components/RTNDatePickerSpecs/Props.h>
#import <react/renderer/components/RTNDatePickerSpecs/RCTComponentViewHelpers.h>

#import <React/RCTConversions.h>

#import "RCTFabricComponentsPlugins.h"

#if __has_include(<react_native_date_picker/react_native_date_picker-Swift.h>)
#import <react_native_date_picker/react_native_date_picker-Swift.h> // Framework target
#else
#import "react_native_date_picker-Swift.h" // App target
#endif

using namespace facebook::react;

@interface RTNDatePicker () <RCTRTNDatePickerViewProtocol,
                             RTNDatePickerUIViewDelegate>
@end

@implementation RTNDatePicker {
  RTNDatePickerUIView *_view;
  RTNDatePickerShadowNode::ConcreteState::Shared _state;
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

    _view = [[RTNDatePickerUIView alloc] initWithFrame:self.bounds];
    _view.delegate = self;

    {
      [_view setTypeWithType:[NSString stringWithUTF8String:defaultViewProps
                                                                .type.c_str()]];
    }

    { [_view setIsOpenWithIsOpen:defaultViewProps.isOpen]; }

    { [_view setIsInlineWithIsInline:defaultViewProps.isInline]; }

    {
      NSMutableSet<NSDate *> *dates =
          [NSMutableSet setWithCapacity:defaultViewProps.value.size()];

      for (auto date : defaultViewProps.value) {
        [dates addObject:[NSDate dateWithTimeIntervalSince1970:date]];
      }

      [_view setValueWithDates:dates];
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

    { [_view setStepWithStep:defaultViewProps.step]; }

    {
      // Codegen props of type string are zero-initialized (undefined values are
      // empty by default)
      NSString *locale =
          defaultViewProps.options.locale.empty()
              ? nil
              : [NSString stringWithUTF8String:defaultViewProps.options.locale
                                                   .c_str()];
      [_view setLocaleWithIdentifier:locale];

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

    {
      [_view setAccentColorWithColor:RCTUIColorFromSharedColor(
                                         defaultViewProps.styles.accentColor)];
    }

    self.contentView = _view;
  }

  return self;
}

- (void)layoutSubviews {
  [super layoutSubviews];

  // Ensure any pending layout is performed
  [_view layoutIfNeeded];

  auto stateData = RTNDatePickerState{
      RCTSizeFromCGSize([_view sizeThatFits:self.bounds.size])};
  _state->updateState(std::move(stateData));
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
    NSMutableSet<NSDate *> *dates =
        [NSMutableSet setWithCapacity:newViewProps.value.size()];

    for (auto date : newViewProps.value) {
      [dates addObject:[NSDate dateWithTimeIntervalSince1970:date]];
    }

    [_view setValueWithDates:dates];
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

  if (oldViewProps.step != newViewProps.step) {
    [_view setStepWithStep:newViewProps.step];
  }

  if (oldViewProps.options.locale != newViewProps.options.locale ||
      oldViewProps.options.confirmText != newViewProps.options.confirmText ||
      oldViewProps.options.cancelText != newViewProps.options.cancelText ||
      oldViewProps.options.mode != newViewProps.options.mode) {
    // Codegen props of type string are zero-initialized (undefined values are
    // empty by default)
    NSString *locale =
        newViewProps.options.locale.empty()
            ? nil
            : [NSString
                  stringWithUTF8String:newViewProps.options.locale.c_str()];
    [_view setLocaleWithIdentifier:locale];

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

  if (oldViewProps.styles.accentColor != newViewProps.styles.accentColor) {
    [_view setAccentColorWithColor:RCTUIColorFromSharedColor(
                                       newViewProps.styles.accentColor)];
  }

  [super updateProps:props oldProps:oldProps];
}

- (void)updateState:(const State::Shared &)state
           oldState:(const State::Shared &)oldState {
  _state =
      std::static_pointer_cast<const RTNDatePickerShadowNode::ConcreteState>(
          state);
}

- (void)onChangeWithDates:(NSSet<NSDate *> *_Nonnull)dates {
  if (_eventEmitter) {
    std::vector<double> value;

    for (NSDate *date in dates) {
      value.push_back(date.timeIntervalSince1970);
    }

    static_cast<const RTNDatePickerEventEmitter &>(*_eventEmitter)
        .onChange({value});
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
