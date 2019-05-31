import NumberUtil from "./NumberUtil";

/**
 * 浏览器缓存写入
 * 采用 localStorage 接口开发
 */
export default {

    /**
     * 缓存键值前缀
     */
    cachePrefix: 'cache_app_h5',
    /**
     * 缓存过期时间键值前缀
     */
    cacheExpPrefix: 'cache_exp_app_h5',
    /**
     * 缓存持续时间前缀
     */
    cacheDurPrefix: 'cache_dur_app_h5',
    tList: {
        'ms': 1,
        's': 1000,
        'h': 3600 * 1000,
        'd': 3600 * 24 * 1000,
    },
    /**
     * 获取 key
     * @param key
     * @returns {string}
     */
    getKey(key) {
        return this.cachePrefix + key;
    },
    /**
     * 获取 过期时间 key
     * @param key
     * @returns {string}
     */
    getKeyExp(key) {
        return this.cacheExpPrefix + key;
    },
    /**
     * 获取 持续时间 key
     * @param key
     * @returns {string}
     */
    getDurExp(key) {
        return this.cacheDurPrefix + key;
    },

    /**
     * 获取缓存中的内容
     * 检查是否存在
     * 存在是否过期
     * 过期则删除缓存内容
     * @param key   需要获取的缓存 key
     * @param def   默认返回（不存在或过期返回内容）
     * @returns {*}
     */
    get(key, def = null) {
        let expTime = this.getLocalStorage(this.getKeyExp(key));
        if (!NumberUtil.isNumber(expTime)) {
            return def;
        }
        expTime = NumberUtil.strToNumber(expTime);
        if (expTime === 0 || expTime >= new Date().getTime()) {
            return this.getLocalStorage(this.getKey(key));
        }
        return def;
    },

    getDelay(key, def = null) {
        let v = this.get(key, def);
        if (v !== null) {
            this.setExpTime(key, this.getLocalStorage(this.getDurExp(key)));
        }
        return v;
    },
    /**
     * 设置一个缓存
     * 是否存在
     * 存在覆盖、不存在新增
     * @param key   键值
     * @param value 内容
     * @param exp   过期时间
     */
    set(key, value, exp = 0) {
        this.setLocaleStorage(this.getKey(key), value);
        if (exp === 0) {
            this.setLocaleStorage(this.getKeyExp(key), 0);
        } else {
            this.setExpTime(key, exp);
        }
    },
    /**
     * 设置过期时间
     * @param key
     * @param exp
     */
    setExpTime(key, exp = 0) {
        let expTime = this.getExpTime(exp);
        this.setLocaleStorage(this.getKeyExp(key), expTime + new Date().getTime());
        this.setLocaleStorage(this.getDurExp(key), this.getExpTime(exp));
    },

    /**
     * 获取过期时间
     * 将字符串时间表达转换成毫秒值
     * @param exp   时间（ 2000 : 2000毫秒 | '6s' : 6秒 | '3h' : 3小时 | '2d' : 2天 )
     * @returns {number}
     */
    getExpTime(exp = 0) {
        let ts = 'h';
        let ep = 0;
        try {
            exp = eval(exp);
        } catch (e) {
        }
        if (typeof exp === 'string') {
            exp = exp.toLowerCase();
            ts = exp.match(/[a-z]+$/ig)[0];
            ep = eval(exp.replace(/[a-z]+$/ig, ''));
        } else {
            ep = exp;
        }
        return ep * this.tList[ts];
    },
    /**
     * 移除缓存
     * 同时移除 内容，过期时间，持续时间
     * @param key
     */
    remove(key) {
        this.removeLocaleStorage(this.getKey(key));
        this.removeLocaleStorage(this.getKeyExp(key));
        this.removeLocaleStorage(this.getDurExp(key));
    },
    /**
     * 移除缓存
     * @param key
     */
    removeLocaleStorage(key) {
        return window.localStorage.removeItem(key);
    },
    /**
     * 设置缓存
     * 长久有效缓存
     * @param key
     * @param value
     */
    setLocaleStorage(key, value) {
        return window.localStorage.setItem(key, JSON.stringify(value));
    },
    /**
     * 获取缓存
     * @param key
     * @returns {*}
     */
    getLocalStorage(key) {
        let v = window.localStorage.getItem(key);
        try {
            return JSON.parse(v);
        } catch (e) {
            return v;
        }
    }

}
