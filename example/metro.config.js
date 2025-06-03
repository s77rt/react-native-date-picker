const {getDefaultConfig, mergeConfig} = require('@react-native/metro-config');

const path = require('path');

const libDir = path.resolve(__dirname, '../');
const extraNodeModules = {
  libDir: libDir,
};

/**
 * Metro configuration
 * https://reactnative.dev/docs/metro
 *
 * @type {import('@react-native/metro-config').MetroConfig}
 */
const config = {
  watchFolders: [libDir],
  resolver: {
    extraNodeModules: new Proxy(extraNodeModules, {
      get: (target, name) =>
        // redirects dependencies referenced from libDir to local node_modules
        name in target
          ? target[name]
          : path.join(process.cwd(), `node_modules/${name}`),
    }),
  },
};

module.exports = mergeConfig(getDefaultConfig(__dirname), config);
