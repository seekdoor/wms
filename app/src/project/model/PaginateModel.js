export default class PaginateModel {
    lastPage = 1;
    pageNum = 0;
    pageSize = 0;
    size = 0;
    total = 0;
    startRow = 0;
    perPageShow = 0;

    /**
     * 构造器
     * 可直接传入页面参数变更回调函数
     * @param changeCallBack
     * @param pageSize
     */
    constructor(changeCallBack = null, pageSize = 14) {
        if (changeCallBack) {
            this.changeCallBack = () => {
                changeCallBack();
            };
        }
        this.pageSize = pageSize;
        this.perPageShow = pageSize;
    }


    /**
     * 设置当前页为下一页
     * @param page
     */
    next(page = null) {
        this.jump(page || this.pageNum + 1);
    }

    /**
     * 跳转到某一页
     * @param page
     */
    jump(page) {
        this.pageNum = page > this.lastPage ? this.lastPage : page;
    }

    /**
     * 跳转到最后一页
     */
    jumpToLast() {
        this.pageNum = this.lastPage;
    }

    /**
     * 将 ajax 获取到的分页信息注入 模型中
     * @param resp
     */
    setPaginate(resp) {
        this.lastPage = resp.lastPage;
        this.pageNum = resp.pageNum;
        this.pageSize = resp.pageSize;
        this.size = resp.size;
        this.startRow = resp.startRow;
        this.total = resp.total;
    }

    /**
     * 转换成 JSON 格式提交
     * @returns {{pageSize: number, pageNum: number}}
     */
    getJSON() {
        return {
            pageNum: this.pageNum,
            pageSize: this.pageSize
        }
    }

    /**
     * 改变当前页触发函数
     */
    changePageCallBack() {
    }

    /**
     * 改变显示数量出发函数
     */
    changeSizeCallBack() {
    }

    /**
     * 改变当前页 和 改变显示数量均会执行
     */
    changeCallBack() {
    }

}