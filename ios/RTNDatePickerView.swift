import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var isOpen: Bool = false
  @Published var isInline: Bool = false
  @Published var value: Date = Date()
  @Published var range: ClosedRange<Date> = Date.distantPast...Date.distantFuture

  init() {}
}

struct RTNDatePickerView: View {
  @ObservedObject var viewModel: RTNDatePickerViewModel
  var onChange: (Date) -> Void
  var onConfirm: () -> Void
  var onCancel: () -> Void

  var datePicker: some View {
    DatePicker(
      "", selection: $viewModel.value, in: viewModel.range, displayedComponents: [.date]
    ).datePickerStyle(
      .graphical
    ).labelsHidden().onChange(
      of: viewModel.value
    ) {
      onChange(viewModel.value)
    }
  }

  var body: some View {
    if viewModel.isInline {
      datePicker
    } else {
      EmptyView().fullScreenCover(isPresented: $viewModel.isOpen) {
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
