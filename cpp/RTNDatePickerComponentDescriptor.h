#pragma once

#include "RTNDatePickerShadowNode.h"

#include <react/renderer/core/ConcreteComponentDescriptor.h>

namespace facebook::react {

/*
 * Descriptor for <RTNDatePicker> component.
 */
class RTNDatePickerComponentDescriptor final
    : public ConcreteComponentDescriptor<RTNDatePickerShadowNode> {
public:
  using ConcreteComponentDescriptor::ConcreteComponentDescriptor;

  void adopt(ShadowNode &shadowNode) const override {
    auto &datePickerShadowNode =
        static_cast<RTNDatePickerShadowNode &>(shadowNode);
    auto &datePickerStateDate = datePickerShadowNode.getStateData();

    datePickerShadowNode.setSize(datePickerStateDate.contentSize);

    ConcreteComponentDescriptor::adopt(shadowNode);
  }
};

} // namespace facebook::react
