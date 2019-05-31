<template>
    <el-table
            class="TableList"
            :data="data"
            style="width: 100%;"
            v-loading="loading"
            @selection-change="selectChange"
    >
        <el-table-column
                width="40"
                fixed
                type="selection"
        ></el-table-column>
        <el-table-column
                type="index"
                fixed
                label="序号"
        ></el-table-column>
        <slot></slot>
    </el-table>
</template>

<script>
    export default {
        name: "TableList",
        components: {},
        props: {
            data: {
                default() {
                    return [];
                }
            },
            value: {
                default: false,
            },
        },
        data() {
            return {
                loading: this.value,
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
            selectChange(nodes, keys) {
                this.$emit('selection-change', nodes, keys);
            },
        },
        watch: {
            loading(v) {
                this.$emit('input', v);
            },
            value(v) {
                this.loading = v;
            }
        },
        computed: {},
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .TableList {
    }
</style>