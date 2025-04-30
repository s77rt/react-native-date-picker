import SwiftUI

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
