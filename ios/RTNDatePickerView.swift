import SwiftUI

struct RTNDatePickerView: View {
  @ObservedObject var viewModel: RTNDatePickerViewModel
  var onChange: (Date) -> Void
  var onConfirm: () -> Void
  var onCancel: () -> Void

  @ViewBuilder
  var datePicker: some View {
    DatePicker(
      selection: $viewModel.value, in: viewModel.range,
      type: viewModel.type == "yearmonth"
        ? .yearmonth
        : viewModel.type == "datetime" ? .datetime : viewModel.type == "time" ? .time : .date,
      mode: viewModel.mode == "wheel" ? .wheel : viewModel.mode == "compact" ? .compact : .graphical
    )
    .onChange(of: viewModel.value) { onChange(viewModel.value) }
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
