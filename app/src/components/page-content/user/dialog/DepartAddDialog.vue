<template>
    <div class="DepartAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${depart.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-form-item label="部门编号">
                    <el-input type="text" v-model="depart.code"></el-input>
                </el-form-item>
                <el-form-item label="部门名称">
                    <el-input type="text" v-model="depart.name"></el-input>
                </el-form-item>
            </el-form>
            <div class="mt-md">
                <el-button-submit
                        :loading="loading"
                        size="small"
                        @click.native="clickSubmit"
                >
                    保存提交
                </el-button-submit>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import DepartModel from "@/project/model/DepartModel";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "DepartAddDialog",
        components: {ElButtonSubmit},
        props: {
            visible: {
                default: false
            },
            editId: {
                default: 0
            }
        },
        data() {
            return {
                depart: new DepartModel(),
                loading: false,
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            showDialog(v) {
                this.$emit('update:visible', v);
            },
            refreshData() {
                if (!this.editId) {
                    return this.depart = new DepartModel;
                }
                this.loading = true;
                this.$ajax.request(Api.depart.getById, {
                    id: this.editId,
                }).then(resp => {
                    this.depart = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.depart.update : Api.depart.insert;
                this.loading = true;
                this.$ajax.request(api, this.depart)
                    .then(resp => {
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                        this.$emit("finish");
                    }).finally(() => this.loading = false)
            },
        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                }
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DepartAddDialog {
    }
</style>