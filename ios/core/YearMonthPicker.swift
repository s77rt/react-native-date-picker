import SwiftUI

struct YearMonthPicker: UIViewRepresentable {
  @Binding var selection: Date
  var `in`: ClosedRange<Date>

  func makeUIView(context: Context) -> UIDatePicker {
    let datePicker = UIDatePicker()

    if #available(iOS 17.4, *) {
      datePicker.datePickerMode = .yearAndMonth
    } else {
      datePicker.datePickerMode = .init(rawValue: 4269) ?? .date
    }

    datePicker.preferredDatePickerStyle = .wheels
    datePicker.minimumDate = `in`.lowerBound
    datePicker.maximumDate = `in`.upperBound

    datePicker.addTarget(
      context.coordinator, action: #selector(Coordinator.updateSelection(_:)), for: .valueChanged)

    return datePicker
  }

  func updateUIView(_ datePicker: UIDatePicker, context: Context) {
    datePicker.date = selection
  }

  func makeCoordinator() -> YearMonthPicker.Coordinator {
    Coordinator(selection: $selection)
  }

  class Coordinator: NSObject {
    private let selection: Binding<Date>

    init(selection: Binding<Date>) {
      self.selection = selection
    }

    @objc func updateSelection(_ sender: UIDatePicker) {
      self.selection.wrappedValue = sender.date
    }
  }
}
