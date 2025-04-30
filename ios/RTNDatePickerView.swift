import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var isOpen: Bool = false
  @Published var value: Date = Date()
  @Published var onChange: (Date) -> Void = { _ in }
  @Published var onConfirm: () -> Void = {}
  @Published var onCancel: () -> Void = {}

  init() {}
}

struct RTNDatePickerView: View {
  @ObservedObject var model: RTNDatePickerViewModel

  var body: some View {
    EmptyView().fullScreenCover(isPresented: $model.isOpen) {
      VStack {
        DatePicker("", selection: $model.value, displayedComponents: [.date]).datePickerStyle(
          .graphical
        ).labelsHidden().onChange(
          of: model.value
        ) {
          model.onChange(model.value)
        }.frame(width: 320)
        Divider()
        HStack {
          Button(
            action: model.onCancel,
            label: { Text("Cancel") }
          )
          Spacer()
          Button(
            action: model.onConfirm,
            label: { Text("Confirm").bold() }
          )
        }
      }
      .frame(width: 320)
      .padding()
      .presentationBackground(.clear).background(Color(UIColor.systemBackground).cornerRadius(20))
    }
  }
}
