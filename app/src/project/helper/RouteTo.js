import RouterUtil from "@/util/RouterUtil";

export default {

    /**
     * 跳转到登陆界面
     * @param query
     */
    loginPage(query = {}) {
        RouterUtil.to('/Login', query);
    }

}