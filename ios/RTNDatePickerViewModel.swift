import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var type: String = "date"
  @Published var isOpen: Bool = false
  @Published var isInline: Bool = false
  @Published var value: Date = Date()
  @Published var range: ClosedRange<Date> = Date.distantPast...Date.distantFuture
  @Published var confirmText: String = "Confirm"
  @Published var cancelText: String = "Cancel"

  init() {}
}
