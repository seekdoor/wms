import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from "./routes";
import Config from "@/assets/Config";

Vue.use(VueRouter);

const router = new VueRouter(routes);

router.beforeEach((to, from, next) => {
    document.querySelector("title").innerHTML = to.meta.title + ' - ' + Config.domainName;
    next();
});
export default router;