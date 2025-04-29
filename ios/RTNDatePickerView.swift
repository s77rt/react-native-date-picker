import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var isOpen: Bool = false
  @Published var value: Date = Date()
  @Published var onChange: (Date) -> Void = { _ in }

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
            action: {},
            label: { Text("Cancel") }
          )
          Spacer()
          Button(
            action: {},
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

@objc public class RTNDatePickerUIView: UIView {
  private let model = RTNDatePickerViewModel()

  @objc override public init(frame: CGRect) {
    super.init(frame: frame)

    model.onChange = dateDidChange

    let view = RTNDatePickerView(model: model)
    let hostingController = UIHostingController(rootView: view)
    hostingController.view.frame = self.bounds
    hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]

    addSubview(hostingController.view)
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func dateDidChange(date: Date) {
    print("dateDidChange", date)
  }

  @objc public func setIsOpen(isOpen: Bool) {
    model.isOpen = isOpen
  }

  @objc public func setValue(date: Date) {
    model.value = date
  }
}
