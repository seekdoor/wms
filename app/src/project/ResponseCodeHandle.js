/**
 * 响应状态码解析
 */
import DialogUtil from "@/util/DialogUtil";
import UserHandle from "@/project/user/UserHandle";
import RouterUtil from "@/util/RouterUtil";

export default class ResponseCodeHandle {

    constructor() {
        this._json = {};
    }

    /**
     * 获取奔雷中的json值
     * @returns {{}|*}
     */
    getJson() {
        return this._json;
    }

    /**
     * 解析响应字符串
     * @returns {boolean}
     * @param responseData
     */
    validate(responseData) {
        this._json = responseData;
        try {
            this._json = (responseData instanceof String) ? JSON.parse(responseData) : responseData;
            return this._identifyCode(this._json);
        } catch (e) {
            return false;
        }
    }

    /**
     * 识别 code 状态码
     * 并执行相应函数操作
     * @private
     */
    _identifyCode() {
        switch (this._json.code) {
            case 200 :
                return this._json;
            case 100 :
                this._code100Handle();
                return false;
            case 110 :
                this._code110Handle();
                return false;
            case 120:
                this._code120Handle();
                return false;
            default :
                this._codeOtherHandle();
                return false;
        }
    }

    /**
     * code == 100
     * 常规错误
     * 将错误信息提示给用户
     * @private
     */
    _code100Handle() {
        DialogUtil.toastError(this._json);
    }

    /**
     * code == 110
     * 未登录错误
     * 用户尚未登录
     * @private
     */
    _code110Handle = () => {
        // UserHandle.removeLoginUser();
        RouterUtil.toLogin();
        DialogUtil.toastError("用户凭证过期，请重新登录！");
    };

    /**
     * code == 120
     * 权限不足
     *
     * @private
     */
    _code120Handle = () => {
        DialogUtil.toastError("您的权限不足，请联系管理员开通！");
    };

    /**
     * 其他操作
     * @private
     */
    _codeOtherHandle = () => {
        DialogUtil.toastError('未知错误');
    };

}