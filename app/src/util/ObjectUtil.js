export default {

    /**
     * 深度 clone
     * 利用序列化和放序列化进行简单的深度 clone 操作
     * @param obj
     * @returns {any}
     */
    deepClone(obj) {
        return JSON.parse(JSON.stringify(obj))
    }

}