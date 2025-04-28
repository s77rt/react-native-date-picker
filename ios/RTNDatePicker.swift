import SwiftUI

struct RTNDatePicker: View {
  var onChange: () -> Void

  @State private var date = Date()

  var body: some View {
    DatePicker("Date", selection: $date, displayedComponents: [.date]).onChange(of: date) {
      print(date)
      self.onChange()
    }
  }
}

@objc public class RTNDatePickerUIHost: UIView {
  @objc override init(frame: CGRect) {
    super.init(frame: frame)

    let view = RTNDatePicker(onChange: dateDidChange)
    let hostingController = UIHostingController(rootView: view)

    hostingController.view.frame = self.bounds
    hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]

    addSubview(hostingController.view)
  }

  required init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func dateDidChange() {
    print("dateDidChange")
  }
}
