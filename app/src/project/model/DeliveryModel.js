import BaseUser from "@/project/model/base/BaseUser";
import DateTimeUtil from "@/util/DateTimeUtil";

export default class DeliveryModel extends BaseUser {

    id = 0;
    stockEntryId = 0;
    companyId = 0;
    number = '';
    stockEntryNumber = '';
    cmopanyCode = '';
    companyName = '';
    companyAddress = '';
    companyUserName = '';
    companyPhone = '';
    companyMobile = '';
    companyEmail = '';
    happenTime = '';
    remark = '';


    constructor() {
        super();
        this.happenTime = DateTimeUtil.dateFormat(new Date())
    }
}