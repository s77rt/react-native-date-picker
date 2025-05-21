#include "RTNDatePickerShadowNode.h"

#include <react/renderer/core/LayoutContext.h>

namespace facebook::react {

extern const char RTNDatePickerComponentName[] = "RTNDatePicker";

#pragma mark - LayoutableShadowNode

Size RTNDatePickerShadowNode::measureContent(
    const LayoutContext & /*layoutContext*/,
    const LayoutConstraints & /*layoutConstraints*/) const {
  return Size{100, 100};
}

} // namespace facebook::react
