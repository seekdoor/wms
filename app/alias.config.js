const path = require('path');
function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {
    resolve: {
        alias: {
            '@': resolve('src'),
            'image': resolve('src/image'),
            'style': resolve('src/style'),
        }
    }
};