#pragma once

#include <jsi/jsi.h>
#include <react/renderer/components/RTNDatePickerSpecs/EventEmitters.h>
#include <react/renderer/components/RTNDatePickerSpecs/Props.h>
#include <react/renderer/components/RTNDatePickerSpecs/States.h>
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

  static ShadowNodeTraits BaseTraits() {
    auto traits = ConcreteViewShadowNode::BaseTraits();
    traits.set(ShadowNodeTraits::Trait::LeafYogaNode);
    traits.set(ShadowNodeTraits::Trait::MeasurableYogaNode);
    return traits;
  }

#pragma mark - LayoutableShadowNode

  Size
  measureContent(const LayoutContext &layoutContext,
                 const LayoutConstraints &layoutConstraints) const override;
};

} // namespace facebook::react
