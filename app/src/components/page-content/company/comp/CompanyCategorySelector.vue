<template>
    <div class="CompanyCategorySelector" style="width: 110px;">
        <el-select :value="valueUse" @input="inputEvent" placeholder="选择分类">
            <el-option label="全部" :value="0" v-if="isFilter"></el-option>
            <el-option :value="item.id "
                       :label="item.label"
                       v-for="(item,i) in category.list()"
                       :key="item.id"
            ></el-option>
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
        computed: {
            valueUse() {
                if (this.value) return this.value;
                return this.isFilter ? 0 : null;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CompanyCategorySelector {
    }
</style>