<template>
    <div class="DashBoardView">
    </div>
</template>

<script>
    import ImageUploadContent from "@/components/common/ImageUploadContent";

    export default {
        name: "DashBoardView",
        components: {ImageUploadContent},
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