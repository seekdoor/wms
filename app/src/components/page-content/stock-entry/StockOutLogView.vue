<template>
    <stock-in-log-view
        :type="2"
    ></stock-in-log-view>
</template>

<script>
    import StockInLogView from "@/components/page-content/stock-entry/StockInLogView";
    export default {
        name: "StockOutLogView",
        components: {StockInLogView},
        props: {},
        data() {
            return {}
        },
        mounted() {
            this.reload();
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
    @import (reference) "~style/all.less";

    .StockOutLogView {
    }
</style>