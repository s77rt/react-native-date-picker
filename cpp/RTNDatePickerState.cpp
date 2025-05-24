#include "RTNDatePickerState.h"

namespace facebook {
namespace react {

#ifdef ANDROID
folly::dynamic RTNDatePickerState::getDynamic() const {
  return folly::dynamic::object("width", contentSize.width)("height",
                                                            contentSize.height);
}
#endif

} // namespace react
} // namespace facebook
