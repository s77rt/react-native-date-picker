import SwiftUI

@objc public protocol RTNDatePickerUIViewDelegate: NSObjectProtocol {
  @objc func onChange(dates: Set<Date>)
  @objc func onConfirm()
  @objc func onCancel()
}
