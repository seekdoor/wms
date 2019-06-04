<template>
    <div class="WindowCardLayout">
        <div class="el-card box-card is-always-shadow">
            <div class="header flex-box flex-center el-card__header"
                 onselectstart="return false"
            >
                <div class="flex-grow">{{ title }}</div>
            </div>
            <div class="body pl-sm pr-sm pt-xs pb-xs ">
                <slot></slot>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "WindowCardLayout",
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

    .WindowCardLayout {
    }
</style>