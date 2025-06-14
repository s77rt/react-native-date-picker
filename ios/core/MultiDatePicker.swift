import SwiftUI

struct MultiDatePicker: View {
  @Binding var selection: Set<DateComponents>
  var `in`: AnyRange<Date>?

  @ViewBuilder
  var body: some View {

    if let `in` = `in` {
      switch `in` {
      case .range(let range):
        SwiftUI.MultiDatePicker("", selection: $selection, in: range)
      case .partialRangeFrom(let partialRangeFrom):
        SwiftUI.MultiDatePicker("", selection: $selection, in: partialRangeFrom)
      case .partialRangeUpTo(let partialRangeUpTo):
        SwiftUI.MultiDatePicker("", selection: $selection, in: partialRangeUpTo)
      }
    } else {
      SwiftUI.MultiDatePicker("", selection: $selection)
    }
  }
}
