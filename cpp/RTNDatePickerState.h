#pragma once

#include <react/renderer/core/graphicsConversions.h>

#ifdef ANDROID
#include <folly/dynamic.h>
#endif

namespace facebook::react {

/*
 * State for <RTNDatePickerState> component.
 */
class RTNDatePickerState final {
public:
  RTNDatePickerState() {};
  RTNDatePickerState(Size contentSize_) : contentSize(contentSize_) {};

#ifdef ANDROID
  RTNDatePickerState(RTNDatePickerState const &previousState,
                     folly::dynamic data)
      : contentSize(Size{(Float)data["width"].getDouble(),
                         (Float)data["height"].getDouble()}) {};
#endif

  const Size contentSize{};

#ifdef ANDROID
  folly::dynamic getDynamic() const;
#endif
};

} // namespace facebook::react
