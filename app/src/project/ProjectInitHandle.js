import VueUtil from "@/util/VueUtil";
import AjaxUtil from "@/util/AjaxUtil";
import RouterUtil from "@/util/RouterUtil";
import Colors from "@/project/enum/Colors";
import Config from "@/assets/Config";
import UserHandle from "@/project/user/UserHandle";
import ImageEnum from "@/project/enum/ImageEnum";
import EcUtil from "@/util/EcUtil";

/**
 * 项目初始化函数
 * 类似 vue 实例化之后执行的函数等等 均放在此处
 */
export default {

    /**
     * 在实例化 Vue 之前执行
     * 可将一些函数注入到Vue原型链中
     * 以遍在项目其他子组件中快速使用
     * @param Vue
     */
    vueInject(Vue) {
        Vue.prototype.$ajax = AjaxUtil;
        Vue.prototype.$_router = RouterUtil;
        Vue.prototype.$_colors = Colors;
        Vue.prototype.$_config = Config;
        Vue.prototype.$_images = ImageEnum
        Vue.prototype.$_ecUtil = EcUtil
    },

    /**
     * vue实例初始化之后执行的函数
     * 将全局 vue 实例附加到 VueUtil 工具中
     * @param vue
     */
    vueInstanceInit(vue) {
        VueUtil.setVue(vue);
        /**
         * 初始化用户信息
         */
        UserHandle.initCheckUser();
    },

}