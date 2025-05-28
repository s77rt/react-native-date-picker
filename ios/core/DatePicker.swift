import SwiftUI

struct DatePicker: View {
  @Binding var selection: Date
  var `in`: ClosedRange<Date>
  var style: String

  @ViewBuilder
  var body: some View {
    if style == "wheel" {
      SwiftUI.DatePicker("", selection: $selection, in: `in`, displayedComponents: .date)
        .labelsHidden().datePickerStyle(.wheel)
    } else if style == "compact" {
      SwiftUI.DatePicker("", selection: $selection, in: `in`, displayedComponents: .date)
        .labelsHidden().datePickerStyle(.compact)
    } else {
      SwiftUI.DatePicker("", selection: $selection, in: `in`, displayedComponents: .date)
        .labelsHidden().datePickerStyle(.graphical)
    }
  }
}
