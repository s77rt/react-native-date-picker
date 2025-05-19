import SwiftUI

struct RTNDatePickerView: View {
  @ObservedObject var viewModel: RTNDatePickerViewModel
  var onChange: (Date) -> Void
  var onConfirm: () -> Void
  var onCancel: () -> Void

  @ViewBuilder
  var datePicker: some View {
    if viewModel.type == "time" {
      DatePicker(
        "", selection: $viewModel.value, in: viewModel.range,
        displayedComponents: .hourAndMinute
      ).datePickerStyle(
        .wheel
      ).labelsHidden().onChange(
        of: viewModel.value
      ) {
        onChange(viewModel.value)
      }
    } else {
      DatePicker(
        "", selection: $viewModel.value, in: viewModel.range,
        displayedComponents: .date
      ).datePickerStyle(
        .graphical
      ).labelsHidden().onChange(
        of: viewModel.value
      ) {
        onChange(viewModel.value)
      }
    }
  }

  @ViewBuilder
  var body: some View {
    if viewModel.isInline {
      datePicker
    } else {
      ZStack {}.fullScreenCover(isPresented: $viewModel.isOpen) {
        ZStack {
          Color.clear.contentShape(Rectangle())
            .onTapGesture {
              onCancel()
            }
          VStack {
            datePicker.frame(width: 320)
            Divider()
            HStack {
              Button(
                action: onCancel,
                label: { Text("Cancel") }
              )
              Spacer()
              Button(
                action: onConfirm,
                label: { Text("Confirm").bold() }
              )
            }
          }
          .frame(width: 320)
          .padding()
          .presentationBackground(Color.black.opacity(0.3))
          .background(Color(UIColor.systemBackground).cornerRadius(20))
        }
      }.transaction({ transaction in
        transaction.disablesAnimations = true
      })
    }
  }
}
