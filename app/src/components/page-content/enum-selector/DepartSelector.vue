<template>
    <div class="DepartSelector">
        <el-select :value="valueUse" @input="change" placeholder="请选择部门">
            <el-option label="所有部门" :value="0" v-if="isFilter"></el-option>
            <el-option v-for="item in departs"
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
        name: "DepartSelector",
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
                departs: []
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
                this.$ajax.request(Api.depart.selectAll)
                    .then(resp => {
                        this.departs = resp;
                    })
            }
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

    .DepartSelector {
    }
</style>