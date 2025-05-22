#pragma once

#include <react/renderer/core/graphicsConversions.h>

namespace facebook::react {

/*
 * State for <RTNDatePickerState> component.
 */
class RTNDatePickerState final {
public:
  RTNDatePickerState() {};
  RTNDatePickerState(Size contentSize_) : contentSize(contentSize_) {};

  const Size contentSize{};
};

} // namespace facebook::react
