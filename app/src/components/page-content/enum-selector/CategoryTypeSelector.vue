<template>
    <div class="CategoryTypeSelector">
        <el-select :value="valueUse" @input="change" placeholder="选择分类">
            <el-option label="全部" :value="0" v-if="isFilter"></el-option>
            <el-option :label="category[1]" :value="1" ></el-option>
            <el-option :label="category[2]" :value="2" ></el-option>
            <el-option :label="category[3]" :value="3" ></el-option>
            <el-option :label="category[4]" :value="4" ></el-option>
            <el-option :label="category[5]" :value="5" ></el-option>
            <el-option :label="category[6]" :value="6" ></el-option>
        </el-select>
    </div>
</template>

<script>
    import CategoryTypeEnum from "@/project/enum/CategoryTypeEnum";

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
                category: CategoryTypeEnum
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