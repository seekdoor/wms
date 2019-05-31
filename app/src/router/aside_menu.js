export default [
    {
        path: '/DashBoard',
        component: () => import('@/components/page-content/dashboard/DashBoardView.vue'),
        meta: {
            title: '统计面板',
            icon: 'iconfont icon-tachometer-alt'
        }
    },
    {
        path: '/UserManager',
        component: () => import("@/components/page-content/user/UserManagerView.vue"),
        meta: {
            title: '用户管理',
            icon: 'iconfont icon-tachometer-alt'
        },
    },
    {
        path: '/CompanyManager',
        component: () => import("@/components/page-content/company/CompanyManagerView.vue"),
        meta: {
            title: '合作方管理',
            icon: 'iconfont icon-tachometer-alt'
        },
    },
]