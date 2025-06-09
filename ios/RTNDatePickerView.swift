import SwiftUI

struct RTNDatePickerView: View {
  @ObservedObject var viewModel: RTNDatePickerViewModel
  var onChange: (Set<Date>) -> Void
  var onConfirm: () -> Void
  var onCancel: () -> Void

  @Environment(\.calendar) private var calendar

  @ViewBuilder
  var datePicker: some View {
    if viewModel.isMultiple {
      MultiDatePicker("", selection: $viewModel.valueMulti)
        .onChange(of: viewModel.valueMulti) {
          var dates = Set<Date>(minimumCapacity: viewModel.valueMulti.count)
          for dateComponents in viewModel.valueMulti {
            if let date = calendar.date(from: dateComponents) {
              dates.insert(date)
            }
          }
          onChange(dates)
        }
    } else {
      DatePicker(
        selection: $viewModel.value, in: viewModel.range,
        type: viewModel.type == "yearmonth"
          ? .yearmonth
          : viewModel.type == "datetime" ? .datetime : viewModel.type == "time" ? .time : .date,
        mode: viewModel.mode == "wheel"
          ? .wheel : viewModel.mode == "compact" ? .compact : .graphical,
        minuteInterval: viewModel.minuteInterval,
        locale: viewModel.locale
      )
      .id("\(viewModel.type)-\(viewModel.mode)")
      .onChange(of: viewModel.value) { onChange(Set(arrayLiteral: viewModel.value)) }
    }
  }

  @ViewBuilder
  var body: some View {
    if viewModel.isInline {
      datePicker.accentColor(viewModel.accentColor)
    } else {
      ZStack {}.fullScreenCover(isPresented: $viewModel.isOpen) {
        ZStack {
          Color.clear.contentShape(Rectangle())
            .onTapGesture {
              onCancel()
            }
          VStack {
            datePicker.fixedSize(horizontal: false, vertical: true)
            Divider()
            HStack {
              Button(
                action: onCancel,
                label: { Text(viewModel.cancelText) }
              )
              Spacer()
              Button(
                action: onConfirm,
                label: { Text(viewModel.confirmText).bold() }
              )
            }
          }
          .frame(width: 320)
          .padding()
          .presentationBackground(Color.black.opacity(0.3))
          .background(Color(UIColor.systemBackground).cornerRadius(20))
          .accentColor(viewModel.accentColor)
        }
      }.transaction({ transaction in
        transaction.disablesAnimations = true
      })
    }
  }
}
