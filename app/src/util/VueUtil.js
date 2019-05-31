export default {
    /**
     * 全局 Vue 实例
     * 也是整个页面的根Vue渲染实例
     */
    _vue: null,
    /**
     * 返回 Vue 实例
     * @returns {null}
     */
    getVue() {
        return this._vue;
    },
    /**
     * 设置当前实例
     * @param vue
     */
    setVue(vue) {
        this._vue = vue;
    }
}