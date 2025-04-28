import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var label: String = ""
  @Published var date: Date = Date()
  @Published var onChange: (Date) -> Void = { _ in }

  init() {}
}

struct RTNDatePickerView: View {
  @ObservedObject var model: RTNDatePickerViewModel

  var body: some View {
    DatePicker(model.label, selection: $model.date, displayedComponents: [.date]).onChange(
      of: model.date
    ) {
      model.onChange(model.date)
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
