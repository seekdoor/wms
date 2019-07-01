import ObjectUtil from "@/util/ObjectUtil";

export default {

    dict: {
        '1': '用户登录',
        '2': '退出登录',
    },


    list(isStockIn = true) {
        return ObjectUtil.dict2List(this.dict);
    }

}