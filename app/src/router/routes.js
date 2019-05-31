import MenuUtil from "@/util/MenuUtil";
import aside_menu from "@/router/aside_menu";

export default {
    routes: [
        {
            path: '/',
            redirect: '/Login'
        },
        {
            // 首页登录页面
            path: '/Login',
            component: () => import("@/components/AppLoginIndex"),
            meta: {
                title: '首页登录',
                icon: ''
            }
        },
        {
            path: '/Back',
            redirect: '/DashBoard',
            component: () => import("@/components/AppBackIndex"),
            meta: {
                title: '后台首页'
            },
            children: MenuUtil.menu2router(aside_menu)
        }
    ]
};
