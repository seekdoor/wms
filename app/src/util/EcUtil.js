import StringUtil from "@/util/StringUtil";

export default {

    /**
     * url 合并
     * 剔除最前和最后的 / 之后进行合并
     * @param urls
     * @returns {string}
     */
    urlMerge(...urls) {
        return urls.map(x => x.replace(/^\/|\/$/ig, '')).join('/');
    },

    /**
     * ids 转换为字符串
     * @param ids
     * @returns {string|*}
     */
    idsFormat(ids) {
        if (typeof ids === 'object') {
            return ids.join(',');
        }
        return ids + '';
    },

    /**
     * 当前运行环境是否为开发环境
     * @returns {boolean}
     */
    isDevelopment() {
        return process.env.NODE_ENV === 'development';
    },

    /**
     * 将 ElementUI table 组件的 column 属性
     * 转换成 排序格式字段
     * @param column
     * @param prefix
     * @returns {{sortOrder: string, sortField: *}|{}}
     */
    getSortField(column, prefix = '') {
        if (!column || !column.column) return {};
        if (prefix !== '') {
            if (!column.prop.includes('.')) {
                column.prop = prefix + '.' + column.prop;
            }
        }
        return {
            'sortField': StringUtil.toLine(column.prop),
            'sortOrder': column.order === 'ascending' ? 'asc' : 'desc'
        }
    },

    /**
     * 初始化传递参数
     * @param vue
     */
    initParams(vue) {
        vue.filterForm = Object.assign(vue.filterForm, vue.params);
        vue.$emit("update:params", {})
    },

    /**
     * 给数值添加都好
     * @param number
     * @param sp
     * @param len
     * @returns {string|...*|string}
     */
    numberDivide(number, sp = ',', len = 3) {
        number += '';
        let arr = number.split('.');
        let zt = [...arr[0]];
        let zr = '';
        for (let i = 0; i < zt.length; i++) {
            if (i % len === 0 && i !== 0) {
                zr = sp + zr;
            }
            zr = zt[zt.length - i - 1] + zr;
        }
        if (typeof arr[1] !== 'undefined' && arr[1].length > 0) {
            zr += '.' + arr[1];
        }
        return zr;
    }

}