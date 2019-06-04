/**
 * 防抖动 和 节流阀 功能
 */
export default {
    /**
     * 默认节流和防抖动延迟时间
     */
    _defDelay: 100,
    /**
     * 节流阀缓存
     */
    _thrKey: {},
    /**
     * 防抖动缓存
     */
    _debKey: {},
    /**
     * 仅执行一次
     */
    _onceKey: {},

    /**
     * 一次性执行完成不再执行
     * @param key
     * @param callback
     */
    once(key, callback) {
        if (this._onceKey[key]) return;
        this._onceKey[key] = true;
        callback && callback();
    },
    /**
     * 节流阀
     * @param key
     * @param callback
     * @param delay
     */
    throttle(key, callback, delay = this._defDelay) {
        if (this._thrKey[key]) return;
        this._thrKey[key] = true;
        callback && callback();
        setTimeout(() => {
            this._thrKey[key] = false;
        }, delay);
    },

    /**
     * 防抖动
     * @param key
     * @param callback
     * @param delay
     */
    debounce(key, callback, delay = this._defDelay + 1) {
        if (this._debKey[key]) clearTimeout(this._debKey[key]);
        this._debKey[key] = setTimeout(() => {
            callback && callback();
        }, delay);
    },

    /**
     * 节流阀与防抖动一起使用
     * @param key
     * @param func
     * @param delay
     */
    thrAndDeb(key, func, delay) {
        delay = delay === null ? 100 : delay;
        this.throttle(key, func, delay);
        this.debounce(key, func, delay);
    },

    /**
     * 节流阀 函数表达式返回
     * @param func  函数
     * @param delay 节流延迟
     * @returns {Function}
     */
    throttleFunction(func, delay = this._defDelay) {
        let isValid = true;
        return (...params) => {
            if (!isValid) return;
            isValid = false;
            func && func(...params);
            setTimeout(() => {
                isValid = true;
            }, delay);
        };
    },
    /**
     * 防抖动 函数表达式返回
     * @param func  函数
     * @param delay 防抖延迟
     * @returns {Function}
     */
    debounceFuntion(func, delay = this._defDelay + 1) {
        let timer;
        return (...params) => {
            clearTimeout(timer);
            timer = setTimeout(() => {
                func && func(...params);
            }, delay);
        }
    },
    /**
     * 节流阀和防抖动函数表达式返回
     * @param func      函数
     * @param delayThrottle 节流延迟
     * @param delayDebounce 防抖延迟
     * @returns {Function}
     */
    thrAndDebFuntion(func, delayThrottle = null, delayDebounce = null) {
        let throttleFunc = this.throttle(func, delayThrottle);
        let debounceFunc = this.debounce(func, delayDebounce);
        return (...params) => {
            throttleFunc(...params);
            debounceFunc(...params);
        }
    },
}