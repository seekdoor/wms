import StringUtil from "@/util/StringUtil";

export default {

    /**
     * 获取浏览器的 hash 对象
     * 函数将自动把 #key=value 转换成对象形式
     * 如传递 key 值，则直接返回对象中的 key 对应的值
     * 如果对象中没有 key 则返回 null
     * @param key   需要快速查询的 key
     * @returns {any}
     */
    hash(key = null) {
        let hash = window.location.hash;
        hash = this.splitKeyValue(hash.replace(/^#/, ''));
        return key === null ? hash : (hash[key] || null);
    },

    /**
     * 获取浏览器的 query 对象
     * 函数将自动把 ?key=value 转换成对象形式
     * 如传递 key 值，则直接返回对象中的 key 对应的值
     * 如果对象中没有 key 则返回 null
     * @param key   需要快速查询的 key
     * @returns {any}
     */
    query(key = null) {
        let query = window.location.search;
        query = this.splitKeyValue(query.replace(/^\?/, ''));
        return key === null ? query : (query[key] || null);
    },

    /**
     * 返回浏览器路径地址
     * 如 http://aaa.com/a/b/c.html 将返回 /a/b/c
     * @returns {string}
     */
    pathName() {
        return window.location.pathname.replace('.html', '');
    },

    /**
     * 分割 k=v&k2=v2 字符串为 对象
     * 将自动把类似 '123', 'true' 的字符串转换为 数字和布尔类型
     * @param str   字符串
     * @returns {{}}
     */
    splitKeyValue(str) {
        let ret = {};
        if (str === '') return {};
        let kvArr = str.split('&');
        kvArr.map(x => {
            let kv = x.split('=');
            let v = null;
            if (kv.length > 1) {
                v = StringUtil.tryConvert(kv[1]);
            }
            ret[kv[0]] = v;
        });
        return ret;
    },

    /**
     * 将对象转换为 hash 字符串
     * 如 #k=v&k2=v2
     * @param obj
     * @param prefix
     * @returns {string}
     */
    toHashString(obj, prefix = true ) {
        if (obj === null || Object.keys(obj).length === 0) {
            return '';
        }
        return ( prefix ? '#' : '') + this.objToKeyValue(obj);
    },

    /**
     * 将对象转换成 query 字符串
     * 带?的字符串
     * @param obj
     * @returns {string}
     */
    toQueryString(obj) {
        if (obj === null || Object.keys(obj).length === 0) {
            return '';
        }
        return '?' + this.objToKeyValue(obj);
    },

    /**
     * 将对象转换成 key=value 形式
     * @param obj
     * @returns {string}
     */
    objToKeyValue(obj) {
        let ret = [];
        for (let k in obj) {
            ret.push(`${k}=${obj[k]}`);
        }
        return ret.join('&');
    }

}
