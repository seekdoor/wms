/**
 * 加载中
 */
import {Message} from "element-ui";
import DialogUtil from "@/util/DialogUtil";

export default {

    /**
     * message 实例
     * 默认动画CSS : el-icon-loading
     */
    _messageInstance: null,
    /**
     * 当前ajax 请求数量
     */
    _ajaxCount: 0,
    /**
     * Ajax 加载中提示延迟出现时间
     */
    _ajaxDelay: 500,
    /**
     * Ajax 超时时间
     */
    _ajaxExpireDelay: 10000,
    /**
     * Ajax 延时计时器
     */
    _ajaxDelayTimeout: null,
    /**
     * Ajax 超时计时器
     */
    _ajaxExpireTimeout: null,

    /**
     * Ajax显示状态
     * 当启动一个 ajax 之后立刻调用次函数
     * 延迟一段时间没有返回后显示加载中动画
     */
    ajaxStart() {
        this._ajaxTimeoutClear();
        this._ajaxDelayTimeout = setTimeout(this.start, this._ajaxDelay);
        this._ajaxExpireDelay = setTimeout(this._ajaxError, this._ajaxExpireDelay);
    },

    /**
     * ajax 超时错误提示
     */
    _ajaxError() {
        this.close();
        DialogUtil.toastError('加载数据超时，请稍候重试！');
    },

    /**
     * 清除 ajax 定时器
     * @private
     */
    _ajaxTimeoutClear() {
        clearTimeout(this._ajaxDelayTimeout);
        clearTimeout(this._ajaxExpireTimeout);
    },

    /**
     * ajax 成功完成
     * 当ajax 请求返回后执行该函数以关闭加载中提示
     */
    ajaxSuccessFinish() {
        if (--this._ajaxCount <= 0) {
            this._ajaxTimeoutClear();
            this.close();
        }
    },


    /**
     * 开始加载中动画
     * 加载中动画不会自动关闭
     * 需 执行 close() 函数关闭动画
     * @param message  加载中附加文字
     * @returns {ElMessageComponent}    message 实例
     */
    start(message = '正在拼命加载中...') {
        this.close();
        return this._messageInstance = Message({
            message: message,
            type: 'info',
            duration: 0,
            iconClass: 'el-icon-loading mr-sm disp-ib'
        })
    },

    /**
     * 关闭加载中动画
     */
    close() {
        if (this._messageInstance) {
            this._messageInstance.close();
        }
    }

}