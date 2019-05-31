export default {


    /**
     * 将侧边菜单转换为路由列表
     * @param menu  菜单列表
     * @returns {Array}
     */
    menu2router(menu = []) {
        let routes = [];
        menu.forEach(x => {
            if (x.component) {
                if (x.children && x.children.length) {
                    x.children = this.menu2router(x.children);
                }
                routes.push(x);
            } else if (x.children && x.children.length) {
                this.menu2router(x.children).forEach(xx => {
                    routes.push(xx);
                })
            }
        });
        return routes;
    }
}