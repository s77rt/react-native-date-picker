import SwiftUI

@objc public protocol RTNDatePickerUIViewDelegate: NSObjectProtocol {
  @objc func onChange(date: Date)
  @objc func onConfirm()
  @objc func onCancel()
}
