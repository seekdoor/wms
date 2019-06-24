import ObjectUtil from "@/util/ObjectUtil";

export default {

    dictIn: {
        '1': '初始录入',
        '2': '审核中',
        '3': '审核通过',
        '4': '审核未通过',
        '5': '已完成',
    },

    dictOut: {
        '1': '初始录入',
        '2': '审核中',
        '3': '审核通过',
        '4': '审核未通过',
        '5': '已发货',
    },

    colors: {
        '1': 'info',
        '2': 'warning',
        '3': 'success',
        '4': 'danger',
        '5': '',
    },

    list(isStockIn = true) {
        return ObjectUtil.dict2List(isStockIn ? this.dictIn : this.dictOut);
    }

}