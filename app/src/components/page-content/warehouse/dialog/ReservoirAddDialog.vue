<template>
    <div class="ReservoirAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${reservoirs.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-form-item label="仓位编号">
                    <el-input type="text" v-model="reservoirs.code"></el-input>
                </el-form-item>
                <el-form-item label="仓位名称">
                    <el-input type="text" v-model="reservoirs.name"></el-input>
                </el-form-item>
                <el-form-item label="所属仓库">
                    <warehouse-selector
                            :level="1"
                            :is-filter="false"
                            :visible="visible"
                            v-model="reservoirs.warehouseId"
                    ></warehouse-selector>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="reservoirs.remark"></el-input>
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
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import ReservoirModel from "@/project/model/ReservoirModel";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";

    export default {
        name: "ReservoirAddDialog",
        components: {WarehouseSelector, ElButtonSubmit},
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
                reservoirs: new ReservoirModel(),
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
                    return this.reservoirs = new ReservoirModel();
                }
                this.loading = true;
                this.$ajax.request(Api.warehouse.resGetById, {
                    id: this.editId,
                }).then(resp => {
                    this.reservoirs = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.warehouse.resUpdate : Api.warehouse.resInsert;
                this.loading = true;
                this.$ajax.request(api, this.reservoirs)
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

    .ReservoirAddDialog {
    }
</style>