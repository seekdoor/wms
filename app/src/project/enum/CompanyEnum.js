import ObjectUtil from "@/util/ObjectUtil";

export default {

    dict: {
        '1': '供应商',
        '2': '客户',
        '3': '承运商',
    },

    list() {
        return ObjectUtil.dict2List(this.dict);
    }
}