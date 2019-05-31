import RouteTo from "@/project/helper/RouteTo";

export default {

    /**
     * 处理用户不存在事件
     */
    userNotExist() {
        RouteTo.loginPage();
    },

    /**
     * 处理 token 无效事件
     */
    tokenNotValid() {

    },


}