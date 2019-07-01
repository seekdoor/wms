export default {


    // 用户常规信息列表
    list: ['/user/list', 'get'],
    // 添加用户
    add: ['/user/add', 'post'],
    // 修改用户信息
    edit: ['/user/edit', 'post'],
    // 删除用户
    remove: ['/user/delete', 'post'],
    // 通过id获取用户
    getUserById: ['/user/get_user_by_id', 'get'],
    // 退出登录
    logout: ['/user/logout', 'post'],
    // 修改我的用户信息
    changeMyInfo: ['/user/change_my_info', 'post'],

}