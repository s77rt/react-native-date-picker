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

  s.source_files    = ["ios/**/*.{h,m,mm,swift}"]

  s.subspec 'componentDescriptors' do |sp|
    sp.source_files = ["cpp/**/*.{h,cpp}"]
  end

  s.pod_target_xcconfig = {
    "CLANG_CXX_LANGUAGE_STANDARD" => "c++20"
  }

  install_modules_dependencies(s)
end
