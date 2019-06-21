<template>
    <el-button
            class="ElButtonMini "
            :size="size"
            :type="type"
            :icon="iconClass"
            :title="title"
            :disabled="disabled"
            :loading="loading"
            :style="buttonStyle"
    >
        <slot></slot>
    </el-button>
</template>

<script>
    export default {
        name: "ElButtonMini",
        components: {},
        props: {
            title: {
                default: ''
            },
            type: {
                default: ''
            },
            size: {
                default: 'mini'
            },
            icon: {
                default: ''
            },
            disabled: {
                default: false
            },
            loading: {
                default: false
            },
            iconColorClass:{
                default : ''
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
        computed: {
            iconClass() {
                let colors = {
                    'delete': ['iconfont icon-trash-alt', 'color-red'],
                    'add': ['iconfont icon-plus', 'color-blue'],
                    'edit': ['iconfont icon-edit', 'color-orange'],
                    'search': ['iconfont icon-search', 'color-orange'],
                };
                let ret = [this.icon, ''];
                if (colors[this.icon]) {
                    ret = colors[this.icon].concat([]);
                }
                ret[1] = this.disabled ? 'color-gray' : ( this.iconColorClass || 'color-black');
                return ret.join(' ');
            },
            buttonStyle() {
                let hasTitle = this.$slots.default || this.title;
                if (hasTitle) {
                    return {};
                }
                return {
                    'padding-left': '7px',
                    'padding-right': '7px'
                }
            }
        },
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .ElButtonMini {
    }
</style>