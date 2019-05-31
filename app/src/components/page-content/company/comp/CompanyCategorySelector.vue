<template>
    <div class="CompanyCategorySelector" style="width: 110px;">
        <el-select :value="value" @input="inputEvent" placeholder="选择分类">
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
        name: "CompanyCategorySelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: true
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
                this.inputEvent(this.isFilter ? 0 : null)
            },
            inputEvent(v) {
                this.$emit('input', v);
            }
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CompanyCategorySelector {
    }
</style>