<template>
    <el-button
            type="primary"
            :style="buttonStyle"
            :icon="icon"
            :size="size"
            :loading="loading"
            :native-type="nativeType"
    >
        <slot></slot>
    </el-button>
</template>

<script>
    export default {
        name: "ElButtonSubmit",
        components: {},
        props: {
            isBlock: {
                default: true
            },
            icon: {
                default: 'iconfont icon-save'
            },
            size: {
                default: 'mini'
            },
            loading: {
                default: false
            },
            nativeType: {
                default: 'submit'
            },

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
        computed: {
            buttonStyle() {
                return {
                    'width': this.isBlock ? '100%' : 'auto'
                }
            }
        },
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .ElButtonSubmit {
    }
</style>