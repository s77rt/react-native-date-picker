cmake_minimum_required(VERSION 3.13)
set(CMAKE_VERBOSE_MAKEFILE on)

file(GLOB componentDescriptors_SRCS CONFIGURE_DEPENDS *.cpp ../../../../cpp/*.cpp)
file(GLOB componentDescriptors_codegen_SRCS CONFIGURE_DEPENDS ../../../build/generated/source/codegen/jni/react/renderer/components/RTNDatePickerSpecs/*.cpp)

add_library(
  react_codegen_RTNDatePickerSpecs
  OBJECT
  ${componentDescriptors_SRCS}
  ${componentDescriptors_codegen_SRCS}
)

target_include_directories(react_codegen_RTNDatePickerSpecs PUBLIC . ../../../../cpp)
target_include_directories(react_codegen_RTNDatePickerSpecs PUBLIC ../../../build/generated/source/codegen/jni)

target_link_libraries(
  react_codegen_RTNDatePickerSpecs
  fbjni
  jsi
  reactnative
)

target_compile_options(
  react_codegen_RTNDatePickerSpecs
  PRIVATE
  -DLOG_TAG=\"ReactNative\"
  -fexceptions
  -frtti
  -std=c++20
  -Wall
)
