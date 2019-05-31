export default {

    /**
     * 检查是否不包含常规字符
     * 检查是否全部为数字
     * 检查是否为 true 或 false
     * @param v
     * @returns {boolean}
     */
    isNotString(v) {
        let l = [
            /^\d+$/,
            /^(true)|(false)$/
        ];
        for (let reg of l) {
            if (reg.test(v)) {
                return true;
            }
        }
        return false;
    },

    /**
     * 尝试将字符串转换为本身类型
     * 如 'true' 转换为 true
     * '123123.20' 转换为 123123.2
     * @param v     需要转换的参数
     * @returns {*}
     */
    tryConvert(v) {
        if (this.isNotString(v)) {
            try {
                v = eval(v);
            } catch (e) {
            }
        }
        return v;
    },
    /**
     * 下划线转换驼峰
     * @param field
     * @returns {*}
     */
    toHump(field) {
        if (!field || field === '') return null;
        return field.replace(/\_(\w)/g, function (all, letter) {
            return letter.toUpperCase();
        });
    },

    /**
     * 驼峰转换下划线
     * @param field
     * @returns {null}
     */
    toLine(field) {
        if (!field || field === '') return null;
        return field.replace(/([A-Z])/g, "_$1").toLowerCase();
    }

}
