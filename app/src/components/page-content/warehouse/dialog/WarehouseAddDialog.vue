<template>
    <div class="WarehouseAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${warehouse.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-form-item label="仓库编号">
                    <el-input type="text" v-model="warehouse.code"></el-input>
                </el-form-item>
                <el-form-item label="仓库名称">
                    <el-input type="text" v-model="warehouse.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="warehouse.remark"></el-input>
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
    import WarehouseModel from "@/project/model/WarehouseModel";

    export default {
        name: "WarehouseAddDialog",
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
                warehouse: new WarehouseModel(),
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
                    return this.warehouse = new WarehouseModel();
                }
                this.loading = true;
                this.$ajax.request(Api.warehouse.warGetById, {
                    id: this.editId,
                }).then(resp => {
                    this.warehouse = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.warehouse.warUpdate : Api.warehouse.warInsert;
                this.loading = true;
                this.$ajax.request(api, this.warehouse)
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

    .WarehouseAddDialog {
    }
</style>