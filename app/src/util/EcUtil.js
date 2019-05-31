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
     * @returns {{sortOrder: string, sortField: *}|{}}
     */
    getSortField(column) {
        console.log(column);
        if (!column) return {};
        return {
            'sortField': StringUtil.toLine(column.prop),
            'sortOrder': column.order === 'ascending' ? 'asc' : 'desc'
        }
    }

}