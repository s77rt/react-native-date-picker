import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var isOpen: Bool = false
  @Published var value: Date = Date()

  init() {}
}

struct RTNDatePickerView: View {
  @ObservedObject var viewModel: RTNDatePickerViewModel
  var onChange: (Date) -> Void
  var onConfirm: () -> Void
  var onCancel: () -> Void

  var body: some View {
    EmptyView().fullScreenCover(isPresented: $viewModel.isOpen) {
      ZStack {
        Color.clear.contentShape(Rectangle())
          .onTapGesture {
            onCancel()
          }
        VStack {
          DatePicker("", selection: $viewModel.value, displayedComponents: [.date]).datePickerStyle(
            .graphical
          ).labelsHidden().onChange(
            of: viewModel.value
          ) {
            onChange(viewModel.value)
          }.frame(width: 320)
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
        .presentationBackground(.ultraThinMaterial).background(
          Color(UIColor.systemBackground).cornerRadius(20))
      }
    }
  }
}
