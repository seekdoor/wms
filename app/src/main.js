/**
 * CSS 样式加载
 */
import "./assets/font_awesome/iconfont.css"
import "./style/all.less"
/**
 * 功能加载
 */
import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import store from './store'
import router from './router'
import ProjectInitHandle from "@/project/ProjectInitHandle"

Vue.config.productionTip = false;

ProjectInitHandle.vueInject(Vue);

let vueInstance = new Vue({
    store,
    router,
    render: h => h(App),
});
// 执行项目初始化函数
ProjectInitHandle.vueInstanceInit(vueInstance);
vueInstance.$mount('#app');
