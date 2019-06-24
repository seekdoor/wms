<template>
    <stock-entry-manager-view
            :type="2"
    ></stock-entry-manager-view>
</template>

<script>
    import StockEntryManagerView from "@/components/page-content/stock-entry/StockEntryManagerView";

    export default {
        name: "StockEntryOutManagerView",
        components: {StockEntryManagerView},
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

    .StockEntryOutManagerView {
    }
</style>