export default class UserModel {

    id = 0;
    userName = '';
    realName = '';
    email = '';
    password = '';
    rePassword = '';
    activated = 0;
    mobile = '';
    phone = '';
    token = '';
    createTime = null;
    gender = 1;
    roleId = 0;
    roleName = '';
    departId = 0;
    departName = '';
    isRoot = 0 ;
    oldPassword = '' ;

    setDefaultActivated(v = 1) {
        this.activated = v;
        return this;
    }
}