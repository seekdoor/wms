import UserModel from "@/project/model/UserModel";

export default class CompanyModel {

    id = 0;
    code = '';
    category = 1;
    name = '';
    address = '';
    contactName = '';
    phone = '';
    mobile = '';
    email = '';
    remark = '';
    creatorId = 0;
    approverId = 0;
    createTime = '';
    creator = new UserModel;
    approver = new UserModel;
    categoryName = '';


    getFilterForm() {
        this.creator = null;
        this.approver = null;
        return this;
    }

}