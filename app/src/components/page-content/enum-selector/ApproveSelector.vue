<template>
    <div class="ApproveSelector">
        <el-select :value="valueUse" @input="change" placeholder="选择审批状态">
            <el-option label="所有状态" :value="0" v-if="isFilter"></el-option>
            <el-option v-for="item in status"
                       :key="item.id"
                       :label="item.label"
                       :value="item.id"
            ></el-option>
        </el-select>
    </div>
</template>

<script>
    import ApproveEnum from "@/project/enum/ApproveEnum";

    export default {
        name: "ApproveSelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: false
            },
            isStockIn: {
                default: true,
            }
        },
        data() {
            return {
                status: ApproveEnum.list(this.isStockIn)
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.change(this.isFilter ? 0 : null);
            },
            change(v) {
                this.$emit('input', v);
            },
            refreshList() {
            }
        },
        watch: {},
        computed: {
            valueUse() {
                if (this.value) {
                    return this.value;
                }
                return this.isFilter ? 0 : null;
            },
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .ApproveSelector {
    }
</style>