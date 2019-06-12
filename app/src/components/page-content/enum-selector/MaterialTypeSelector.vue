<template>
    <div class="CategoryTypeSelector">
        <el-select :value="valueUse" @input="change" splaceholder="选择类别">
            <el-option label="全部" :value="0" v-if="isFilter"></el-option>
            <el-option :label="types[1]" :value="1"></el-option>
            <el-option :label="types[2]" :value="2"></el-option>
            <el-option :label="types[3]" :value="3"></el-option>
        </el-select>
    </div>
</template>

<script>
    import MaterialTypeEnum from "@/project/enum/MaterialTypeEnum";

    export default {
        name: "CategoryTypeSelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: false
            }
        },
        data() {
            return {
                types: MaterialTypeEnum
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
        },
        watch: {},
        computed: {
            valueUse() {
                if (this.value) {
                    return this.value;
                }
                return this.isFilter ? 0 : null;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CategoryTypeSelector {
    }
</style>