const merge = require("webpack-merge");
const common = require("./webpack.common.js");

module.exports = merge(common, {
  output: {
    filename: `[name].js`
  },
  devServer: {
    port: 8090,
    compress: true,
    proxy: {
      "**": "http://localhost:8080"
    },
    open: true,
    openPage: "",
    publicPath: "/gen-js/",
    overlay: true
  }
});
