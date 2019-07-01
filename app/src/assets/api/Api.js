import api_LogReg from "@/assets/api/api_LogReg";
import api_user from "@/assets/api/api_user";
import api_rbac from "@/assets/api/api_rbac";
import api_goods from "@/assets/api/api_goods";

export default {
    /**
     * 用户登录注册接口
     */
    logReg: api_LogReg,
    /**
     * 用户相关接口
     */
    user: api_user,
    /**
     * rbac 权限管理相关接口
     */
    rbac: api_rbac,
    /**
     * 图片相关接口
     */
    image: {
        // 上传图片 base64
        uploadBase64: ['/back/image/uploadBase64', 'post']
    },
    /**
     * 商品接口
     */
    goods: api_goods,

    /**
     * 合作方接口
     */
    company: {
        list: ['/company/list', 'get'],
        add: ['/company/add', 'post'],
        edit: ['/company/edit', 'post'],
        delete: ['/company/delete', 'post'],
        getById: ['/company/get_by_id', 'get'],
        selectByCategory: ['/company/select_by_category', 'get']
    },

    role_auth: {
        // 角色
        authAll: ['/role_auth/auth_all', 'get'],
        authDelete: ['/role_auth/auth_delete', 'post'],
        authInsert: ['/role_auth/auth_insert', 'post'],
        authUpdate: ['/role_auth/auth_update', 'post'],
        getAuthById: ['/role_auth/get_auth_by_id', 'get'],
        // 权限
        getRoleById: ['/role_auth/get_role_by_id', 'get'],
        roleAll: ['/role_auth/role_all', 'get'],
        roleDelete: ['/role_auth/role_delete', 'post'],
        roleInsert: ['/role_auth/role_insert', 'post'],
        roleList: ['/role_auth/role_list', 'get'],
        roleUpdate: ['/role_auth/role_update', 'post'],

    },
    // 部门
    depart: {
        list: ['/depart/list', 'get'],
        selectAll: ['/depart/select_all', 'get'],
        getById: ['/depart/get_by_id', 'get'],
        insert: ['/depart/insert', 'post'],
        update: ['/depart/update', 'post'],
        delete: ['/depart/delete', 'post'],
    },
    // 分类字典
    category: {
        delete: ['/category/delete', 'post'],
        getById: ['/category/get_by_id', 'get'],
        insert: ['/category/insert', 'post'],
        list: ['/category/list', 'get'],
        selectByType: ['/category/select_by_type', 'get'],
        update: ['/category/update', 'post']
    },
    warehouse: {
        resAllByWarehouseId: ['/warehouse/res_all_by_warehouse_id', 'get'],
        resDelete: ['/warehouse/res_delete', 'post'],
        resGetById: ['/warehouse/res_get_by_id', 'get'],
        resInsert: ['/warehouse/res_insert', 'post'],
        resList: ['/warehouse/res_list', 'get'],
        resUpdate: ['/warehouse/res_update', 'post'],
        stockAllByReservoirId: ['/warehouse/stock_all_by_reservoir_id', 'get'],
        stockDelete: ['/warehouse/stock_delete', 'post'],
        stockGetById: ['/warehouse/stock_get_by_id', 'get'],
        stockInsert: ['/warehouse/stock_insert', 'post'],
        stockList: ['/warehouse/stock_list', 'get'],
        stockUpdate: ['/warehouse/stock_update', 'post'],
        warDelete: ['/warehouse/war_delete', 'post'],
        warGetById: ['/warehouse/war_get_by_id', 'get'],
        warInsert: ['/warehouse/war_insert', 'post'],
        warList: ['/warehouse/war_list', 'get'],
        warUpdate: ['/warehouse/war_update', 'post'],
        warSelectAll: ['/warehouse/war_select_all', 'get'],
        resSelectAll: ['/warehouse/res_select_all', 'get'],
        stockSelectAll: ['/warehouse/stock_select_all', 'get'],
    },
    material: {
        list: ['/material/list', 'get'],
        delete: ['/material/delete', 'post'],
        getById: ['/material/get_by_id', 'get'],
        insert: ['/material/insert', 'post'],
        update: ['/material/update', 'post'],
        selectByType: ['/material/select_by_types', 'get']
    },
    stockEntry: {
        delete: ['/stock_entry/delete', 'post'],
        getById: ['/stock_entry/get_by_id', 'get'],
        insert: ['/stock_entry/insert', 'post'],
        list: ['/stock_entry/list', 'get'],
        update: ['/stock_entry/update', 'post'],
        approve: ['/stock_entry/approve', 'post'],
        finish: ['/stock_entry/finish', 'post'],
        submit: ['/stock_entry/submit', 'post'],
        deliveryFinish: ['/stock_entry/delivery_finish', 'post'],
    },
    move: {
        selectByStockEntryId: ['/move/select_by_stock_entry_id', 'get'],
        selectByStockTransId: ['/move/select_by_stock_trans_id', 'get'],
        insert: ['/move/insert', 'post'],
        delete: ['/move/delete', 'post'],
        getById: ['/move/get_by_id', 'get'],
        update: ['/move/update', 'post'],
        stockInList: ['/move/stock_in_list', 'get'],
        stockOutList: ['/move/stock_out_list', 'get'],
        transList: ['/move/trans_list', 'get'],
    },
    inventory: {
        selectAllMaterialIds: ['/inventory/select_all_material_ids', 'get'],
        selectAllStockIds: ['/inventory/select_all_stock_ids', 'get'],
        selectAllStockIdsByMtlIds: ['/inventory/select_all_stock_ids_by_mtl_ids', 'get'],
        getByMtlAndStk: ['/inventory/get_by_mtl_and_stk', 'get'],
        selectAllMtlIdsByStkId: ['/inventory/select_all_mtl_ids_by_stk_id', 'get'],
        list: ['/inventory/list', 'get'],
    },
    stockTrans: {
        approve: ['/stock_trans/approve', 'post'],
        delete: ['/stock_trans/delete', 'post'],
        finish: ['/stock_trans/finish', 'post'],
        getById: ['/stock_trans/get_by_id', 'get'],
        insert: ['/stock_trans/insert', 'post'],
        list: ['/stock_trans/select_where', 'get'],
        submit: ['/stock_trans/submit', 'post'],
        update: ['/stock_trans/update', 'post'],
    },
    logger: {
        loginRecordList: ['/logger/login_record_list', 'get'],
    }

}