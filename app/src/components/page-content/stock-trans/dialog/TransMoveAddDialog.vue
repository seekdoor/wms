<template>
    <div class="TransMoveAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="400px"
                :title="title"
                :close-on-click-modal="false"
        >
            <el-form label-position="top">
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`物料或产品名称`" :required="true">
                            <MaterialSelector
                                    :types="'1,2,3'"
                                    v-model="move.materialId"
                                    width="100%"
                                    :filter-ids="mtlFilterIds"
                                    :need-filter="true"
                            ></MaterialSelector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item :label="`当前剩余库存数量`" :required="true">
                            <el-input :disabled="true" v-model="inventory.quantity"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item :label="`移出数量`" :required="true">
                            <el-input-integer
                                    :is-float="true"
                                    v-model="move.quantity"
                                    :max="inventory.quantity"
                            ></el-input-integer>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`备注`" :required="false">
                            <el-input
                                    type="textarea"
                                    v-model="move.remark"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <div class="text-right mt-xs">
                <el-button-submit
                        :loading="loading"
                        :is-block="false"
                        @click.native="clickSubmit"
                >保存提交
                </el-button-submit>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import CategorySelector from "@/components/page-content/enum-selector/CategorySelector";
    import CompanySelector from "@/components/page-content/enum-selector/CompanySelector";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import MaterialSelector from "@/components/page-content/enum-selector/MaterialSelector";
    import MoveModel from "@/project/model/MoveModel";
    import ElInputInteger from "@/components/common/input/ElInpuInteger";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import InventoryModel from "@/project/model/InventoryModel";
    import StockTransModel from "@/project/model/StockTransModel";

    export default {
        name: "TransMoveAddDialog",
        components: {
            WarehouseSelector,
            ElInputInteger, MaterialSelector, ElButtonSubmit, CompanySelector, CategorySelector
        },
        props: {
            type: {
                default: 1
            },
            visible: {
                default: false
            },
            editId: {
                default: 0,
            },
            /** @type StockTransModel */
            stockTrans: {
                default() {
                    return new StockTransModel()
                }
            }
        },
        data() {
            return {
                loading: false,
                move: new MoveModel(),
                stkFilterIds: [],
                mtlFilterIds: [],
                inventory: new InventoryModel()
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
                this.refreshSelectorFilter();
                if (!this.editId) {
                    let st = this.stockTrans;
                    this.move = new MoveModel();
                    this.move.quantity = 0;
                    this.move.stockTransId = st.id;
                    this.move.type = 3;
                    this.move.warehouseId = st.warehouseId;
                    this.move.reservoirId = st.reservoirId;
                    this.move.stockId = st.stockId;
                    this.move.warehouseTid = st.warehouseTid;
                    this.move.reservoirTid = st.reservoirTid;
                    this.move.stockTid = st.stockTid;
                    this.move.status = st.status;
                    return;
                }
                this.$ajax.request(Api.move.getById, {
                    id: this.editId
                }).then(resp => {
                    this.move = resp;
                });
            },

            refreshSelectorFilter() {
                this.inventory = new InventoryModel();
                this.$ajax.request(Api.inventory.selectAllMtlIdsByStkId, {
                    stkId: this.stockTrans.stockId
                }).then(resp => {
                    this.mtlFilterIds = resp;
                })
            },

            refreshInventory() {
                this.$ajax.request(Api.inventory.getByMtlAndStk, this.inventory).then(resp => {
                    this.inventory = resp || new InventoryModel;
                })
            },

            clickSubmit() {
                this.loading = true;
                let api = this.editId ? Api.move.update : Api.move.insert;
                this.$ajax.request(api, this.move).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                    this.$emit('finish');
                }).finally(() => this.loading = false)
            },
        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                }
            },
            'move.materialId'(v) {
                this.inventory.materialId = v;
                this.inventory.stockId = this.stockTrans.stockId;
                this.refreshInventory();
            },

        },
        computed: {
            titleText() {
                return this.type === 1 ? '入库明细' : '出库明细';
            },
            materialTypes() {
                return this.type === 1 ? '1,3' : '2,3';
            },
            title() {
                return this.editId ? `编辑 ${this.titleText} ${this.editId}` : `新增 ${this.titleText}`
            },
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .TransMoveAddDialog {
    }
</style>