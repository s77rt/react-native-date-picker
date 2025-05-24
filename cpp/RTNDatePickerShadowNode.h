#pragma once

#include "RTNDatePickerState.h"

#include <jsi/jsi.h>
#include <react/renderer/components/RTNDatePickerSpecs/EventEmitters.h>
#include <react/renderer/components/RTNDatePickerSpecs/Props.h>
#include <react/renderer/components/view/ConcreteViewShadowNode.h>

namespace facebook::react {

JSI_EXPORT extern const char RTNDatePickerComponentName[];

/*
 * `ShadowNode` for <RTNDatePicker> component.
 */
class RTNDatePickerShadowNode final
    : public ConcreteViewShadowNode<
          RTNDatePickerComponentName, RTNDatePickerProps,
          RTNDatePickerEventEmitter, RTNDatePickerState> {
public:
  using ConcreteViewShadowNode::ConcreteViewShadowNode;
};

} // namespace facebook::react
