export default {

    /**
     * 判断元素是否隐藏
     * @param el
     * @returns {boolean}
     */
    isHidden(el) {
        let style = window.getComputedStyle(el);
        return (style.display === 'none')
    }
}