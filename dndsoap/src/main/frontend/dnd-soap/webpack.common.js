const webpack = require("webpack");
const path = require("path");

module.exports = {
  resolve: { extensions: [".js", ".jsx"] },
  entry: {
    index: "./src/index.js"
  },
  output: {
    path: path.resolve(__dirname, "../../resources/static/gen-js/")
  },
  devtool: "source-map",
  resolveLoader: {
    modules: ["./node_modules"]
  },
  module: {
    rules: [
      {
        test: /\.(jpg|png|svg)$/,
        loader: "url-loader",
        options: {
          limit: 25000
        }
      },

      {
        test: /\.jsx?$/,
        include: path.resolve(__dirname, "./src"),
        exclude: /node_modules/,
        loader: "babel-loader"
      },
      {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      },
      {
        test: /\.gif$/,
        loader: "url-loader",
        options: {
          mimetype: "image/png"
        }
      },
      {
        test: /\.woff(2)?(\?v=[0-9].[0-9].[0-9])?$/,
        loader: "url-loader",
        options: {
          mimetype: "application/font-woff"
        }
      },
      {
        test: /\.(ttf|eot|svg)(\?v=[0-9].[0-9].[0-9])?$/,
        loader: "file-loader",
        options: {
          name: "[name].[ext]"
        }
      }
    ]
  }
};
