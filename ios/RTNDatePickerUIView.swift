import SwiftUI

@objc public class RTNDatePickerUIView: UIView {
  @objc public weak var delegate: RTNDatePickerUIViewDelegate?

  private let viewModel = RTNDatePickerViewModel()
  private var lastValueUpdate: Date = Date()  // Default value in the view model

  @objc override public init(frame: CGRect) {
    super.init(frame: frame)

    let view = RTNDatePickerView(
      viewModel: viewModel, onChange: onChange, onConfirm: onConfirm, onCancel: onCancel)
    let hostingController = UIHostingController(rootView: view)
    hostingController.view.frame = self.bounds
    hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]

    addSubview(hostingController.view)
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  private func onChange(date: Date) {
    if lastValueUpdate == date {
      return
    }
    lastValueUpdate = date
    delegate?.onChange(date: date)
  }

  private func onConfirm() {
    delegate?.onConfirm()
  }

  private func onCancel() {
    delegate?.onCancel()
  }

  @objc public func setType(type: String) {
    viewModel.type = type
  }

  @objc public func setIsOpen(isOpen: Bool) {
    viewModel.isOpen = isOpen
  }

  @objc public func setIsInline(isInline: Bool) {
    viewModel.isInline = isInline
  }

  @objc public func setValue(date: Date) {
    // Changing the value programmatically shouldn't trigger the onChange event
    lastValueUpdate = date

    viewModel.value = date
  }

  @objc public func setRange(lowerBound: Date, upperBound: Date) {
    viewModel.range = lowerBound...upperBound
  }

  @objc public func setConfirmText(confirmText: String?) {
    let localizedKey = String.LocalizationValue(stringLiteral: confirmText ?? "Done")
    viewModel.confirmText = String(localized: localizedKey)
  }

  @objc public func setCancelText(cancelText: String?) {
    let localizedKey = String.LocalizationValue(stringLiteral: cancelText ?? "Cancel")
    viewModel.cancelText = String(localized: localizedKey)
  }
}
