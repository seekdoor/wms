import {Message, MessageBox} from "element-ui";

export default {

    /**
     * 信息格式化
     * 针对 ajax 响应码进行整理辨别
     * 是字符串则直接打印
     * 是对象这查看对象下是否有 message
     * 有则返回 message 属性
     * @param message
     * @returns {*}
     * @private
     */
    _messageFormat(message) {
        if (typeof message === 'undefined') {
            return '消息内容未定义！';
        }
        if (typeof message === 'string') {
            return message;
        }
        if (typeof message === 'object' && message.message) {
            return message.message;
        }
        return '提示信息格式错误！';
    },

    /**
     * 顶部提示默认显示时间
     */
    _toastDefaultOption() {
        return {
            type: 'info',
            showClose: true,
            duration: 5000,
        }
    },

    /**
     * 常规顶部显示信息
     * @param message  信息
     * @param option   选型
     */
    toast(message, option = {}) {
        let opt = Object.assign(this._toastDefaultOption(), option);
        opt.message = this._messageFormat(message);
        return Message(opt);
    },

    /**
     * 成功显示toast
     * @param message
     * @returns {*}
     */
    toastSuccess(message) {
        return this.toast(message, {
            type: 'success'
        });
    },
    /**
     * 错误提示
     * @param message
     * @returns {*}
     */
    toastError(message) {
        return this.toast(message, {
            type: 'error'
        });
    },
    /**
     * 警告提示
     * @param message
     * @returns {*}
     */
    toastWarning(message) {
        return this.toast(message, {
            type: 'warning'
        });
    },

    /**
     * 确认对话框
     * @param message
     * @param title
     * @returns {Promise<MessageBoxData>}
     */
    confirm(message, title = '提示') {
        message = `
            <span class="word-wrap">
                ${message}
            </span>
        `;
        return MessageBox.confirm(message, title, {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            dangerouslyUseHTMLString: true,
        })
    }


}