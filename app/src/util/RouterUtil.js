import VueUtil from "@/util/VueUtil";
import UrlUtil from "@/util/UrlUtil";
import CollectionUtil from "@/util/CollectionUtil";
import aside_menu from "@/router/aside_menu";
import ObjectUtil from "@/util/ObjectUtil";
import Windows from "@/project/var/Windows";

export default {

    /**
     * 原始跳转方法映射
     * 接受 vue-router 的默认 route 阐述
     * {
     *     path : '/index' ,
     *     query: {
                var1: yes
            }
     * }
     * @param route
     */
    toRoute(route) {
        VueUtil.getVue().$router.push(route)
    },

    /**
     * 跳转到 path 路径
     * @param path  路径
     * @param query 参数
     */
    toPath(path, query = {}) {
        this.toRoute({
            path: path,
            query: query
        })
    },

    /**
     * 跳转函数
     * 自动分辨传入参数是否为原生参数还是字符串路径
     * 根据不同参数类型执行 toRoute 或 toPath
     * @param to    跳转路径 path 或 原生路由
     * @param params    参数
     */
    to(to, params = {}) {
        if (typeof to === 'string') {
            this.toPath(to, params);
        } else {
            this.toRoute(to);
        }
    },

    /**
     * 设置 hash 参数
     * @param params
     */
    setHash(params = {}) {
        let nowHash = UrlUtil.hash();
        nowHash = Object.assign(nowHash, params);
        location.hash = UrlUtil.toHashString(nowHash, false);
    },

    /**
     * 设置 query 参数
     * 用于翻页浏览器地址保存
     * @param params
     */
    setQuery(params = {}) {
        let nowQuery = UrlUtil.query();
        nowQuery = Object.assign(nowQuery, params);
        location.query = UrlUtil.toQueryString(nowQuery);
    },

    /**
     * 跳转到登录界面
     * @param query
     */
    toLogin(query = {}) {
        this.toRoute({
            path: '/Login',
            query: query
        })
    },
    /**
     * 跳转到后台
     * @param query
     */
    toBack(query = {}) {
        this.toRoute({
            path: '/Back',
            query: query
        })
    },

    toWindow(path, query = {}) {
        let route = CollectionUtil.findDeep(aside_menu, x => x.path === path);
        route.params = query;
        Windows.set(route);
    }
}