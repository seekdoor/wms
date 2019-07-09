import VueUtil from "@/util/VueUtil";
import CacheUtil from "@/util/CacheUtil";
import AjaxUtil from "@/util/AjaxUtil";
import Api from "@/assets/api/Api";
import AuthValidateHandle from "@/project/user/AuthValidateHandle";

export default {

    /**
     * 当前登录用户
     */
    _user: null,
    /**
     * 登录用户在缓存中的key值
     */
    _cache_key: "login_user_cache" + (process.env.NODE_ENV === 'production' ? '_production' : ''),
    /**
     * 设置状态管理器 isLogin 当前状态
     * @param status    登录状态 false ：未登录， true ： 已登录
     * @private
     */
    _setIsLoginStore(status) {
        VueUtil.getVue().$store.dispatch("setIsLogin", status);
    },
    /**
     * 设置登录用户
     * 将登录用户写入缓存
     * @param user 用户
     */
    setLoginUser(user) {
        this._user = user;
        CacheUtil.set(this._cache_key, user);
        this._setIsLoginStore(!!user);
    },
    /**
     * 移除登录用户
     * 从缓存中移除登录用户信息
     */
    removeLoginUser() {
        this._user = false;
        CacheUtil.remove(this._cache_key);
        this._setIsLoginStore(false);
    },

    /**
     * 初始化检查用户是否存在登录缓存
     * 存在则暂时认为用户已经登录并保存用户
     * 用户页面刷新时候进行初始化
     */
    initCheckUser() {
        let user = CacheUtil.get(this._cache_key, null);
        this.setLoginUser(user);
        if (!user) {
            AuthValidateHandle.userNotExist();
        }
    },

    /**
     * 获取当前登录用户
     * @returns {null}
     */
    getLoginUser() {
        return this._user;
    },
    /**
     * 查看当前用户是否处于登录状态
     * @returns {boolean}
     */
    isLogin() {
        return !!this.getLoginUser();
    },

    /**
     * 刷新当前登录用户信息
     * 可传入一个回调函数来处理自定义内容
     * @param callback
     */
    refreshUser(callback = null) {
        AjaxUtil.request(Api.user.myInfo)
            .then(user => {
                this.setLoginUser(user);
                callback && callback(user);
            })
    },

}