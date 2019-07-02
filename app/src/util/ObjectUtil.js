export default {

    /**
     * 深度 clone
     * 利用序列化和放序列化进行简单的深度 clone 操作
     * @param obj
     * @returns {any}
     */
    deepClone(obj) {
        return JSON.parse(JSON.stringify(obj))
    },

    /**
     * 将字典转换成 list
     * @param dict
     * @param idKey
     * @param labelKey
     * @returns {Array}
     */
    dict2List(dict = {}, idKey = 'id', labelKey = 'label') {
        let ret = [];
        for (let i in dict) {
            let D = dict[i];
            let c = {};
            try {
                i *= 1;
            } catch (ignore) {
            }
            c[idKey] = i;
            c[labelKey] = D;
            ret.push(c);
        }
        return ret;
    }

}