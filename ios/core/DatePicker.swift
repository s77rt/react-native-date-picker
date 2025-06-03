import SwiftUI

enum DatePickerType {
  case date
  case time
  case datetime
  case yearmonth
}

enum DatePickerMode {
  case wheel
  case compact
  case graphical
}

struct DatePicker: UIViewRepresentable {
  @Binding var selection: Date
  var `in`: ClosedRange<Date>
  var type: DatePickerType  // DatePickerType is used as UIDatePicker.Mode
  var mode: DatePickerMode  // Not to be confused with UIDatePicker.Mode. DatePickerMode is used as UIDatePickerStyle
  var minuteInterval: Int

  func makeUIView(context: Context) -> UIDatePicker {
    let datePicker = UIDatePicker()

    datePicker.datePickerMode =
      switch type {
      case .date:
        .date
      case .time:
        .time
      case .datetime:
        .dateAndTime
      case .yearmonth:
        if #available(iOS 17.4, *) { .yearAndMonth } else { .init(rawValue: 4269) ?? .date }
      }

    datePicker.preferredDatePickerStyle =
      switch mode {
      case .wheel:
        .wheels
      case .compact:
        .compact
      case .graphical:
        .inline
      }

    datePicker.addTarget(
      context.coordinator, action: #selector(Coordinator.updateSelection(_:)), for: .valueChanged)

    return datePicker
  }

  func updateUIView(_ datePicker: UIDatePicker, context: Context) {
    datePicker.date = selection

    datePicker.minimumDate = `in`.lowerBound
    datePicker.maximumDate = `in`.upperBound

    datePicker.minuteInterval = minuteInterval
  }

  func sizeThatFits(
    _ proposal: ProposedViewSize,
    uiView: UIDatePicker, context: Context
  ) -> CGSize? {
    let proposedWidth = proposal.width ?? 0
    let proposedHeight = proposal.height ?? 0
    let datePickerSize = uiView.sizeThatFits(CGSize(width: proposedWidth, height: proposedHeight))

    return CGSize(
      width: max(proposedWidth, datePickerSize.width),
      height: max(proposedHeight, datePickerSize.height))
  }

  func makeCoordinator() -> DatePicker.Coordinator {
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
