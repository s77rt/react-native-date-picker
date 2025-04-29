import SwiftUI

class RTNDatePickerViewModel: ObservableObject {
  @Published var date: Date = Date()
  @Published var onChange: (Date) -> Void = { _ in }

  init() {}
}

struct RTNDatePickerView: View {
  @ObservedObject var model: RTNDatePickerViewModel

  var body: some View {
    DatePicker("", selection: $model.date, displayedComponents: [.date]).datePickerStyle(
      .compact
    ).labelsHidden()
      .onChange(
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
    hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]

    addSubview(hostingController.view)
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func dateDidChange(date: Date) {
    print("dateDidChange", date)
  }

  @objc public func setDate(date: Date) {
    model.date = date
  }
}
