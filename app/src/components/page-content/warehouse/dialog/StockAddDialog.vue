<template>
    <div class="StockAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${stock.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-form-item label="货架编号">
                    <el-input type="text" v-model="stock.code"></el-input>
                </el-form-item>
                <el-form-item label="货架名称">
                    <el-input type="text" v-model="stock.name"></el-input>
                </el-form-item>
                <el-form-item label="所属仓位">
                    <warehouse-selector
                            width="100%"
                            :level="2"
                            :is-filter="false"
                            :visible="visible"
                            :value="stock.reservoirId"
                            @change="warehouseSelectorChange"
                    ></warehouse-selector>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="stock.remark"></el-input>
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
    import StockModel from "@/project/model/StockModel";

    export default {
        name: "StockAddDialog",
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
                stock: new StockModel(),
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
                    return this.stock = new StockModel();
                }
                this.loading = true;
                this.$ajax.request(Api.warehouse.stockGetById, {
                    id: this.editId,
                }).then(resp => {
                    this.stock = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.warehouse.stockUpdate : Api.warehouse.stockInsert;
                this.loading = true;
                this.$ajax.request(api, this.stock)
                    .then(resp => {
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                        this.$emit("finish");
                    }).finally(() => this.loading = false)
            },
            warehouseSelectorChange(nodesIds) {
                this.stock.warehouseId = nodesIds[0];
                this.stock.reservoirId = nodesIds[1];
            }
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

    .StockAddDialog {
    }
</style>