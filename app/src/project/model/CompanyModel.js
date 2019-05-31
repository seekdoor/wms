import UserModel from "@/project/model/UserModel";

export default class CompanyModel {

    id = 0;
    code = '';
    category = 0;
    name = '';
    address = '';
    contactName = '';
    phone = '';
    mobile = '';
    email = '';
    remark = '';
    createUid = 0;
    approveUid = 0;
    createTime = '';
    createUser = new UserModel;
    approveUser = new UserModel;
    categoryName = '';


    getFilterForm() {
        this.createUser = null;
        this.approveUser = null;
        return this;
    }

}