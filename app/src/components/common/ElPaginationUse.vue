<template>
    <div class="ElPaginationUse">
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="paginate.pageNum"
                :page-sizes="[paginate.perPageShow, 30, 50, 100]"
                :page-size="paginate.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="paginate.total"
        ></el-pagination>
    </div>
</template>

<script>
    import PaginateModel from "@/project/model/PaginateModel";

    export default {
        name: "ElPaginationUse",
        components: {},
        props: {
            paginate: {
                default() {
                    return new PaginateModel()
                }
            }
        },
        data() {
            return {}
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            handleSizeChange(a) {
                this.paginate.pageSize = a;
                this.paginate.changeSizeCallBack();
                this.paginate.changeCallBack();
            },
            handleCurrentChange(a) {
                this.paginate.jump(a);
                this.paginate.changePageCallBack();
                this.paginate.changeCallBack();
            }
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import "~style/var.less";

    .ElPaginationUse {
    }
</style>