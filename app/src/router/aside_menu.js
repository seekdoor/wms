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
                path: '/MaterialManager',
                component: () => import("@/components/page-content/material/MaterialManagerView.vue"),
                meta: {
                    title: '物料管理',
                },
            },
            {
                path: '/ProductManager',
                component: () => import("@/components/page-content/material/ProductManagerView.vue"),
                meta: {
                    title: '产品管理',
                },
            },
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

    {
        path: '/WarehouseManager',
        meta: {
            title: '库房信息管理',
            icon: 'iconfont icon-box-open'
        },
        children: [
            {
                path: '/WarehouseManager',
                component: () => import("@/components/page-content/warehouse/WarehouseManagerView.vue"),
                meta: {
                    title: '仓库管理',
                },
            },
            {
                path: '/ReservoirManager',
                component: () => import("@/components/page-content/warehouse/ReservoirManagerView.vue"),
                meta: {
                    title: '库区管理',
                },
            },
            {
                path: '/StockManager',
                component: () => import("@/components/page-content/warehouse/StockManagerView.vue"),
                meta: {
                    title: '货架管理',
                },
            },
        ]
    },

    {
        path: '/InOutManager',
        meta: {
            title: '出入库管理',
            icon: 'iconfont icon-exchange-alt'
        },
        children: [
            {
                path: '/StockInManager',
                component: () => import("@/components/page-content/stock-entry/StockEntryManagerView.vue"),
                meta: {
                    title: '入库管理',
                },
            },
            {
                path: '/StockOutManager',
                component: () => import("@/components/page-content/stock-entry/StockEntryOutManagerView.vue"),
                meta: {
                    title: '出库管理',
                },
            },
            {
                path: '/StockTransManager',
                component: () => import("@/components/page-content/stock-trans/StockTransManagerView.vue"),
                meta: {
                    title: '库存移位',
                },
            },
            {
                path: '/InventorySearchView',
                component: () => import("@/components/page-content/inventory/InventorySearchView.vue"),
                meta: {
                    title: '库存记录',
                },
            },
            {
                path: '/StockInLogView',
                component: () => import("@/components/page-content/stock-entry/StockInLogView.vue"),
                meta: {
                    title: '入库记录',
                },
            },
            {
                path: '/StockOutLogView',
                component: () => import("@/components/page-content/stock-entry/StockOutLogView.vue"),
                meta: {
                    title: '出库记录',
                },
            },
        ]
    },

    {
        path: '/LoggerList',
        component: () => import("@/components/page-content/user/UserManagerView.vue"),
        meta: {
            title: '日志查询',
            icon: 'iconfont icon-clipboard-list'
        },
        children: [
            {
                path: '/LoginRecordView',
                component: () => import("@/components/page-content/logger/LoginRecordView.vue"),
                meta: {
                    title: '用户登录日志',
                },
            },

        ]
    },
]