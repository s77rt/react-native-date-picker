import SwiftUI

class RTNDatePickerModel: ObservableObject {
  @Published var label: String = ""
  @Published var onChange: () -> Void = {}

  init() {}
}

struct RTNDatePicker: View {
  @ObservedObject var model: RTNDatePickerModel

  @State private var date = Date()

  var body: some View {
    DatePicker(model.label, selection: $date, displayedComponents: [.date]).onChange(of: date) {
      print(date)
      model.onChange()
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

  private func dateDidChange() {
    print("dateDidChange")
  }

  @objc public func setLabel(label: String) {
    model.label = label
  }
}
