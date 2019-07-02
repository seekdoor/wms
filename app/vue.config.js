const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir);
}

const webpack = require('webpack');


module.exports = {
    devServer: {
        port: 20003
    },
    outputDir : resolve("dist") ,
    publicPath: "./",
    chainWebpack: (config) => {
        config.resolve.alias
            .set('@', resolve('src'))
            .set('image', resolve('src/image'))
            .set('style', resolve('src/style'))
    },
    configureWebpack: {
        plugins: [
            new webpack.DefinePlugin({}),
        ]
    }
};