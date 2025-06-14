import React
import SwiftUI

@objc public class RTNDatePickerUIView: UIView {
  @objc public weak var delegate: RTNDatePickerUIViewDelegate?

  private var hostingController: UIHostingController<RTNDatePickerView>?

  private let viewModel = RTNDatePickerViewModel()
  private var lastValueUpdate: Set<Date>? = nil

  @objc override public init(frame: CGRect) {
    super.init(frame: frame)

    let view = RTNDatePickerView(
      viewModel: viewModel, onChange: onChange, onConfirm: onConfirm, onCancel: onCancel)

    self.hostingController = UIHostingController(rootView: view)

    if let hostingController = self.hostingController {
      addSubview(hostingController.view)
      hostingController.view.translatesAutoresizingMaskIntoConstraints = false
      NSLayoutConstraint.activate([
        hostingController.view.leadingAnchor.constraint(equalTo: self.leadingAnchor),
        hostingController.view.trailingAnchor.constraint(equalTo: self.trailingAnchor),
        hostingController.view.topAnchor.constraint(equalTo: self.topAnchor),
        hostingController.view.bottomAnchor.constraint(equalTo: self.bottomAnchor),
      ])

      reactAddController(toClosestParent: hostingController)
    }
  }

  required public init?(coder: NSCoder) {
    fatalError("init?(coder: NSCoder) is not implemented")
  }

  override public func sizeThatFits(_ size: CGSize) -> CGSize {
    if let hostingController = self.hostingController {
      return hostingController.view.sizeThatFits(size)
    }

    return CGSize.zero
  }

  private func onChange(dates: Set<Date>) {
    if lastValueUpdate == dates {
      return
    }
    lastValueUpdate = dates
    delegate?.onChange(dates: dates)
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

  @objc public func setIsMultiple(isMultiple: Bool) {
    viewModel.isMultiple = isMultiple
  }

  @objc public func setIsInline(isInline: Bool) {
    viewModel.isInline = isInline
  }

  @objc public func setValue(dates: Set<Date>) {
    // Changing the value programmatically shouldn't trigger the onChange event
    lastValueUpdate = dates

    if let date = dates.first {
      viewModel.value = date
    }

    var datesComponents = Set<DateComponents>(minimumCapacity: dates.count)
    for date in dates {
      datesComponents.insert(Calendar.current.dateComponents(in: TimeZone.current, from: date))
    }
    viewModel.valueMulti = datesComponents
  }

  @objc public func setRange(lowerBound: Date?, upperBound: Date?) {
    if let lb = lowerBound, let ub = upperBound {
      viewModel.range = AnyRange.range(lb..<ub)
    } else if let lb = lowerBound {
      viewModel.range = AnyRange.partialRangeFrom(lb...)
    } else if let ub = upperBound {
      viewModel.range = AnyRange.partialRangeUpTo(..<ub)
    } else {
      viewModel.range = nil
    }
  }

  @objc public func setStep(step: Int) {
    // Step is zero if no value is provided. Use default (1)
    if step == 0 {
      viewModel.minuteInterval = 1
      return
    }

    viewModel.minuteInterval = step
  }

  @objc public func setLocale(identifier: String?) {
    if let localeIdentifier = identifier {
      viewModel.locale = Locale(identifier: localeIdentifier)
      return
    }

    viewModel.locale = nil
  }

  @objc public func setConfirmText(confirmText: String?) {
    let localizedKey = String.LocalizationValue(stringLiteral: confirmText ?? "Done")
    viewModel.confirmText = String(localized: localizedKey)
  }

  @objc public func setCancelText(cancelText: String?) {
    let localizedKey = String.LocalizationValue(stringLiteral: cancelText ?? "Cancel")
    viewModel.cancelText = String(localized: localizedKey)
  }

  @objc public func setMode(mode: String) {
    viewModel.mode = mode
  }

  @objc public func setAccentColor(color: UIColor?) {
    if let uiColor = color {
      viewModel.accentColor = Color(uiColor)
      return
    }

    viewModel.accentColor = Color.accentColor
  }
}
