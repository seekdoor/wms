<template>
    <div class="PageLayout">
        <div class="">
            <slot name="header"></slot>
        </div>
        <div class="mt-sm flex-grow">
            <slot name="content"></slot>
        </div>
        <div class="mt-sm">
            <slot name="foot"></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PageLayout",
        components: {},
        props: {},
        data() {
            return {}
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

    .PageLayout {
    }
</style>