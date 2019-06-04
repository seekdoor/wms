export default class RoleModel {
    id = 0;
    name = '';
    remark = '';
    createUid = '';
    updateUid = '';
    createTime = '';
    updateTime = '';
    createUserName = '';
    updateUserName = '';
    /**
     * 权限列表
     * @type {Array<AuthModel>}
     */
    auths = []
}