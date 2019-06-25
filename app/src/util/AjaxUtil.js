import Config from "@/assets/Config";
import axios from 'axios';
import ResponseCodeHandle from "@/project/ResponseCodeHandle";
import UserHandle from "@/project/user/UserHandle";
import DialogUtil from "@/util/DialogUtil";
import StatesContainer from "@/project/StatesContainer";

export default class AjaxUtil {

    static _ajaxCount = 0;
    static _UrlRequestMap = new Map();

    option = {};

    constructor(url = '', params = {}) {
        /**
         * axios 配置选项
         */
        this.option = {
            baseURL: Config.domain,
            method: 'post',
            url: url,
            data: params,
            contentType: "application/json",
            responseType: 'text',
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                // 'Content-Type': 'application/x-www-form-urlencoded',
                'Content-Type': 'application/json',
            },
            validateStatus: function (status) {
                return true;
            },
        };
        this.option.transformRequest = [(d, h) => {
            return JSON.stringify(d);
            // return this._transformRequest(d, h);
        }];
        this.responseCodeHandel = new ResponseCodeHandle;
        this._ajaxIndex = ++AjaxUtil._ajaxCount;
    }

    /**
     * 在 data 提交之前的预处理函数
     * @param data
     * @param header
     * @returns {string}
     * @private
     */
    _transformRequest(data, header) {
        return this._urlEncode(data);
    }

    /**
     * 快速POST操作函数
     * @param url   url 地址
     * @param params 参数
     */
    static post(url, params = {}) {
        let ajax = new AjaxUtil();
        ajax.setNormalInfo(url, params, 'post');
        return ajax.request();
    }

    /**
     * 快速 get 操作函数
     * @returns {Promise<AxiosResponse<any>|never>}
     */
    static get(url, params = {}) {
        let ajax = new AjaxUtil();
        ajax.setNormalInfo(url, params, 'get');
        return ajax.request();
    }

    /**
     * 快速综合请求
     * 使用 Api 配置中的 Api 对象进行请求
     * 如果 api 传入为字符串，直接执行 post 函数
     * 如果为数组：api[0]表示url， api[1]表示 method,
     * 如果为对象：api.url, api.method+
     * @param api
     * @param params
     * @param method
     * @returns {Promise<AxiosResponse<any>|never>}
     */
    static request(api, params = {}, method = null) {
        let [url] = [''];
        if (api instanceof String) {
            url = api;
            method = method || 'post';
        } else if (api instanceof Array) {
            url = api[0];
            method = (api.length > 1 && method == null ? api[1] : method) || 'post';
        } else if (api instanceof Object) {
            url = api.url;
            method = method || api.method;
        }
        let ajax = new AjaxUtil();
        ajax.setNormalInfo(url, params, method);
        return ajax.request();
    }

    /**
     * 快速返回一个本类 ajax 对象
     * 可以进行链式操作
     * @returns {AjaxUtil}
     */
    static ajax(url, params) {
        return new AjaxUtil(url, params);
    }

    /**
     * 请求函数
     * 发情向服务器的请求
     * 在此之前必须配置好其他参数
     * @returns {Promise<AxiosResponse<any> | never>}
     */
    request() {
        this._printRequest();
        this._urlFormat();
        this.setGlobalHeader();
        return axios.request(this.option)
            .then(resp => {
                return this._thenHandle(resp);
            })
            .catch(err => {
                this._requestErrorHandler(err);
                return Promise.reject(err);
            })
    }



    /**
     * 请求错误情况返回
     * 处理AJAX错误情况
     * 如果错误为 http 状态码错误，则打开错误详情窗口
     */
    _requestErrorHandler = err => {
        if (typeof err === 'object' && err.status) {
            StatesContainer.info.ajaxErrorInfo = err.data;
            if (err.status !== 200) {
                DialogUtil.toastError(`未知错误[code:${err.status}]`);
                StatesContainer.layout.errorWindowVisible = true;
            }
        }
    };

    /**
     * 设置全局 header 头部
     * 用户 token 追加到Header验证字段(Authorization)
     */
    setGlobalHeader() {
        if (UserHandle.isLogin()) {
            this.setHeaders({
                'Authorization': UserHandle.getLoginUser().token
            });
        }
    }

    /**
     * url格式化
     * 检查 请求 method 是否为 get
     * 是的话将 参数 urlEncode 之后追加到 url 地址中
     * @private
     */
    _urlFormat() {
        let query = this._urlEncode((this.option.data));
        if (this.option.method === 'get') {
            this.option.url += '?' + query;
        }
    }

    /**
     * ajax 请求正常返回处理
     * 将 resp.data 交给项目 code 解析函数解析
     * @param resp
     * @private
     */
    _thenHandle(resp) {
        if (resp.status !== 200) {
            throw resp;
        }
        let res = this.responseCodeHandel.validate(resp.data);
        let ret = null;
        if (res) {
            ret = Promise.resolve(res.data);
        } else {
            res = this.responseCodeHandel.getJson();
            ret = Promise.reject(resp)
        }
        this._printResponse(res);
        return ret;
    }

    /**
     * 打印响应结果
     * @param res
     * @private
     */
    _printResponse(res) {
        console.log({
            ajaxId: this._ajaxIndex + '_resp',
            fromUrl: this.option.url,
            response: res,
            option: this.option,
        })
    }

    /**
     * 打印请求信息
     * @private
     */
    _printRequest() {
        console.log({
            ajaxId: this._ajaxIndex + '',
            toUrl: this.option.url,
            request: this.option.data,
            option: this.option,
        })
    }

    /**
     * 设置常规信息
     * @param url   接口地址
     * @param params    参数
     * @param method    请求方法
     */
    setNormalInfo(url, params, method = 'post') {
        this.option.url = url;
        this.option.data = params;
        this.option.method = method;
        return this;
    }

    /**
     * 设置 axios 的头信息
     * @param headers
     */
    setHeaders(headers) {
        this.option.headers = Object.assign(this.option.headers, headers);
        return this;
    }

    /**
     * url编码
     * 将 JSON 格式转换成 key=value&key=value格式
     * 并将 value 做 url 编码
     * @param param
     * @param key
     * @returns {string}
     */
    _urlEncode(param, key) {
        let paramStr = "";
        if (typeof param === "string" || typeof param === 'number' || typeof param === 'boolean') {
            paramStr += "&" + key + "=" + encodeURIComponent(param);
        } else {
            for (let i in param) {
                let item = param[i];
                let k = key == null ? i : key + (true || param instanceof Array ? "[" + i + "]" : "." + i);
                paramStr += '&' + this._urlEncode(item, k);
            }
        }
        return paramStr.substr(1);
    }
}