<template>
    <div class="CategorySelector">
        <el-select :value="valueUse" @input="change" :placeholder="placeholder">
            <el-option :label="filterAllText" :value="0" v-if="isFilter"></el-option>
            <el-option v-for="item in categories"
                       :key="item.id"
                       :label="item.name"
                       :value="item.id"
            ></el-option>
        </el-select>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";

    export default {
        name: "CategorySelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: false
            },
            type: {
                default: 0
            },
            placeholder: {
                default: '请选择'
            },
            filterAllText: {
                default: '全部'
            }
        },
        data() {
            return {
                categories: []
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshList();
                this.change(this.isFilter ? 0 : null);
            },
            change(v) {
                this.$emit('input', v);
            },
            refreshList() {
                this.$ajax.request(Api.category.selectByType, {
                    type: this.type
                }).then(resp => {
                    this.categories = resp;
                })
            }
        },
        watch: {
            type(){
                this.refreshList();
            }
        },
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

    .CategorySelector {
    }
</style>