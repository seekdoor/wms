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
            title: '系统管理',
            icon: 'iconfont icon-cog'
        },
        children: [
            {
                path: '/UserManager',
                component: () => import("@/components/page-content/user/UserManagerView.vue"),
                meta: {
                    title: '用户管理',
                },
            },
            {
                path: '/DepartManager',
                component: () => import("@/components/page-content/user/DepartManagerView.vue"),
                meta: {
                    title: '部门管理',
                },
            },
            {
                path: '/RoleManager',
                component: () => import("@/components/page-content/user/RoleManagerView.vue"),
                meta: {
                    title: '角色权限管理',
                },
            },
        ]
    },

    {
        path: '/BaseManager',
        component: () => import("@/components/page-content/user/UserManagerView.vue"),
        meta: {
            title: '基础资料',
            icon: 'iconfont icon-cubes'
        },
        children: [
            {
                path: '/CompanyManager',
                component: () => import("@/components/page-content/company/CompanyManagerView.vue"),
                meta: {
                    title: '合作方管理',
                },
            },
            {
                path: '/CategoryManager',
                component: () => import("@/components/page-content/company/CategoryManagerView.vue"),
                meta: {
                    title: '分类字典管理',
                },
            },
        ]
    },


]