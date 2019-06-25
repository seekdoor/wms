export default {

    /**
     * 时间戳格式化字符串
     * @param ts
     * @param format
     * @returns {string}
     */
    tsFormat(ts, format = '%Y-%m-%d %H:%i:%s') {
        let time = new Date(ts * 1000);
        return this.dateTimeFormat(time, format);
    },

    /**
     * 时间格式化
     * @param time
     * @param format
     * @returns {string}
     */
    dateTimeFormat(time = new Date, format = '%Y-%m-%d %H:%i:%s') {
        format = format.replace('%Y', time.getFullYear());
        format = format.replace('%m', this.padStartZero(time.getMonth() + 1));
        format = format.replace('%d', this.padStartZero(time.getDate()));
        format = format.replace('%H', this.padStartZero(time.getHours()));
        format = format.replace('%i', this.padStartZero(time.getMinutes()));
        format = format.replace('%s', this.padStartZero(time.getSeconds()));
        return format;
    },

    /**
     * 时间格式化
     * @param time
     * @param format
     * @returns {string}
     */
    dateFormat(time = new Date(), format = '%Y-%m-%d') {
        return this.dateTimeFormat(time, format);
    },

    /**
     * 常用日期格式
     * @param ts
     * @param format
     * @returns {*|string}
     */
    tsDateFormat(ts, format = '%Y-%m-%d') {
        return this.tsFormat(ts, format);
    },

    /**
     * 补齐两位数数值
     * @param str
     * @param num
     * @returns {string}
     */
    padStartZero(str, num = 2) {
        return (str + '').padStart(num, '0');
    },

}