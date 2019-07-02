<template>
    <div class="DashBoardView fz-sm">
        <dash-board-top-cards></dash-board-top-cards>
        <div class="flex-box mt-xs flex-wrap">
            <div class="mr-xs" style="width: 500px;">
                <dash-board-product-chart></dash-board-product-chart>
            </div>
            <div class="mr-xs" style="width: 600px;">
                <dash-board-stock-out-chart></dash-board-stock-out-chart>
            </div>
            <div class="flex-grow" style="width: 300px;">
                <dash-board-need-list></dash-board-need-list>
            </div>
        </div>
    </div>
</template>

<script>
    import ImageUploadContent from "@/components/common/ImageUploadContent";
    import DashBoardTopCards from "@/components/page-content/dashboard/content/DashBoardTopCards";
    import DashBoardProductChart from "@/components/page-content/dashboard/content/DashBoardProductChart";
    import DashBoardStockOutChart from "@/components/page-content/dashboard/content/DashBoardStockOutChart";
    import DashBoardNeedList from "@/components/page-content/dashboard/content/DashBoardNeedList";

    export default {
        name: "DashBoardView",
        components: {
            DashBoardNeedList,
            DashBoardStockOutChart, DashBoardProductChart, DashBoardTopCards, ImageUploadContent},
        props: {},
        data() {
            return {
                list: []
            }
        },
        mounted() {
            this.reload();
        },
        activated() {
        },
        methods: {
            reload() {
            },
        },
        watch: {},
        computed: {},
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .DashBoardView {

    }
</style>