import SwiftUI

class RTNDatePickerModel: ObservableObject {
  @Published var label: String = ""
  @Published var date: Date = Date()
  @Published var onChange: (Date) -> Void = { _ in }

  init() {}
}

struct RTNDatePicker: View {
  @ObservedObject var model: RTNDatePickerModel

  var body: some View {
    DatePicker(model.label, selection: $model.date, displayedComponents: [.date]).onChange(
      of: model.date
    ) {
      model.onChange(model.date)
    }
  }
}

@objc public class RTNDatePickerUIHost: UIView {
  private let model = RTNDatePickerModel()

  @objc override public init(frame: CGRect) {
    super.init(frame: frame)

    model.onChange = dateDidChange

    let view = RTNDatePicker(model: model)
    let hostingController = UIHostingController(rootView: view)
    hostingController.view.frame = self.bounds
    hostingController.view.translatesAutoresizingMaskIntoConstraints = false

    addSubview(hostingController.view)
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func dateDidChange(date: Date) {
    print("dateDidChange", date)
  }

  @objc public func setLabel(label: String) {
    model.label = label
  }

  @objc public func setDate(date: Date) {
    model.date = date
  }
}
