<template>
    <div class="CompanySelector">
        <el-select :value="valueUse" @input="change" placeholder="选择分类">
            <el-option label="全部" :value="0" v-if="isFilter"></el-option>
            <el-option :label="category[1]" :value="1"></el-option>
            <el-option :label="category[2]" :value="2"></el-option>
            <el-option :label="category[3]" :value="3"></el-option>
        </el-select>
    </div>
</template>

<script>
    import CompanyEnum from "@/project/enum/CompanyEnum";

    export default {
        name: "CompanySelector",
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
                category: CompanyEnum
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

    .CompanySelector {
    }
</style>