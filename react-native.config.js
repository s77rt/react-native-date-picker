module.exports = {
	dependency: {
		platforms: {
			android: {
				componentDescriptors: ["RTNDatePickerComponentDescriptor"],
				cmakeListsPath: "./src/main/jni/CMakeLists.txt",
			},
		},
	},
};
