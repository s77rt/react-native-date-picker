import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var type: String = "date"
  @Published var isOpen: Bool = false
  @Published var isInline: Bool = false
  @Published var value: Date = Date()
  @Published var range: ClosedRange<Date> = Date.distantPast...Date.distantFuture
  @Published var minuteInterval: Int = 1
  @Published var confirmText: String = "Done"
  @Published var cancelText: String = "Cancel"
  @Published var mode: String = "graphical"
  @Published var accentColor: Color = Color.accentColor

  init() {}
}
