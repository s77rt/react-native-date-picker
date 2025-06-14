import SwiftUI

enum AnyRange<Bound> where Bound: Comparable {
  case range(Range<Bound>)
  case partialRangeFrom(PartialRangeFrom<Bound>)
  case partialRangeUpTo(PartialRangeUpTo<Bound>)
}

class RTNDatePickerViewModel: ObservableObject {
  @Published var type: String = "date"
  @Published var isOpen: Bool = false
  @Published var isMultiple: Bool = false
  @Published var isInline: Bool = false
  @Published var value: Date = Date()  // DatePicker
  @Published var valueMulti: Set<DateComponents> = Set<DateComponents>()  // MultiDatePicker
  @Published var range: AnyRange<Date>? = nil
  @Published var minuteInterval: Int = 1
  @Published var locale: Locale? = nil
  @Published var confirmText: String = "Done"
  @Published var cancelText: String = "Cancel"
  @Published var mode: String = "graphical"
  @Published var accentColor: Color = Color.accentColor

  init() {}
}
