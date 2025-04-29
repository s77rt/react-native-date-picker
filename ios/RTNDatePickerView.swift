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

@objc public protocol RTNDatePickerUIViewDelegate: NSObjectProtocol {
  @objc func onChange(date: Date)
  @objc func onConfirm()
  @objc func onCancel()
}

@objc public class RTNDatePickerUIView: UIView {
  @objc public weak var delegate: RTNDatePickerUIViewDelegate?

  private let model = RTNDatePickerViewModel()

  @objc override public init(frame: CGRect) {
    super.init(frame: frame)

    model.onChange = onChange
    model.onConfirm = onConfirm
    model.onCancel = onCancel

    let view = RTNDatePickerView(model: model)
    let hostingController = UIHostingController(rootView: view)
    hostingController.view.frame = self.bounds
    hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]

    addSubview(hostingController.view)
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func onChange(date: Date) {
    delegate?.onChange(date: date)
  }

  private func onConfirm() {
    delegate?.onConfirm()
  }

  private func onCancel() {
    delegate?.onCancel()
  }

  @objc public func setIsOpen(isOpen: Bool) {
    model.isOpen = isOpen
  }

  @objc public func setValue(date: Date) {
    model.value = date
  }
}
