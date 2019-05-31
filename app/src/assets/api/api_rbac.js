export default {
    // 添加权限
    addAuth: ['/back/rbac/addAuth', 'post'],
    // 权限列表
    authList: ['/back/rbac/authList', 'get'],
    // 编辑权限
    authEdit: ['/back/rbac/authEdit', 'post'],
    // 删除权限
    authDelete: ['/back/rbac/authDelete', 'post'],


    // 添加角色
    addRole: ['/back/rbac/addRole', 'post'],
    // 角色列表
    roleList: ['/back/rbac/roleList', 'get'],
    // 编辑角色
    roleEdit: ['/back/rbac/roleEdit', 'post'],
    // 删除角色
    removeRole: ['/back/rbac/removeRole', 'post'],


    // 绑定用户角色
    userRoleRelation: ['/back/rbac/userRoleRelation', 'post'],
    // 绑定角色权限
    roleAuthRelation: ['/back/rbac/roleAuthRelation', 'post'],

    // 用户列表带权限 - 分页
    userRoleList : ['/back/rbac/userRoleList', 'get'],
}