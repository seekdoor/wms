<template>
    <div class="TitleHead fz-lg pb-sm">
        <slot>{{title}}</slot>
    </div>
</template>

<script>
    export default {
        name: "TitleHead",
        components: {},
        props: {
            title: {
                default: ''
            }
        },
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

    .TitleHead {
    }
</style>