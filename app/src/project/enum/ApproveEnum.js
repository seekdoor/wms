import ObjectUtil from "@/util/ObjectUtil";

export default {

    dict: {
        '1': '初始提交',
        '2': '审核中',
        '3': '审核通过',
        '4': '审核未通过',
        '5': '已完成',
    },

    list() {
        return ObjectUtil.dict2List(this.dict);
    }

}