require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name            = "react-native-date-picker"
  s.version         = package["version"]
  s.summary         = package["description"]
  s.description     = package["description"]
  s.homepage        = package["homepage"]
  s.license         = package["license"]
  s.platforms       = { :ios => "17.0" }
  s.author          = package["author"]
  s.source          = { :git => "", :tag => "#{s.version}" }

  s.source_files    = ["ios/**/*.{mm,swift}"]

  s.subspec 'componentDescriptors' do |sp|
    sp.source_files = ["cpp/**/*.{cpp}"]
  end

  s.preserve_paths = [
    "ios/**/*.h",
    "cpp/**/*.h"
  ]

  s.pod_target_xcconfig = {
    "CLANG_CXX_LANGUAGE_STANDARD" => "c++20",
    "HEADER_SEARCH_PATHS" => "\"$(PODS_TARGET_SRCROOT)/cpp/\"/** "
  }

  install_modules_dependencies(s)
end
