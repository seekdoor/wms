<template>
    <div class="MoveAddDialog">
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
                            ></MaterialSelector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item :label="`计划数量`" :required="true">
                            <el-input-integer
                                    :is-float="true"
                                    v-model="move.planQuantity"
                            ></el-input-integer>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item :label="`实际数量`" :required="true">
                            <el-input-integer
                                    :is-float="true"
                                    v-model="move.quantity"
                            ></el-input-integer>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`货架`" :required="true">
                            <warehouse-selector
                                    :level="3"
                                    :value="move.stockId"
                                    @change="warehouseChange"
                                    width="100%"
                            ></warehouse-selector>
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
                        :is-block="false"
                        @click.native="clickSubmit"
                >保存提交
                </el-button-submit>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import StockEntryModel from "@/project/model/StockEntryModel";
    import CategorySelector from "@/components/page-content/enum-selector/CategorySelector";
    import CompanySelector from "@/components/page-content/enum-selector/CompanySelector";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import MaterialSelector from "@/components/page-content/enum-selector/MaterialSelector";
    import MoveModel from "@/project/model/MoveModel";
    import ElInputInteger from "@/components/common/input/ElInpuInteger";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";

    export default {
        name: "MoveAddDialog",
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
            stockEntry: {
                default() {
                    return new StockEntryModel()
                }
            }
        },
        data() {
            return {
                move: new MoveModel()
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
                    this.move = new MoveModel();
                    this.move.quantity = 0;
                    this.move.companyId = this.stockEntry.companyId;
                    this.move.stockEntryId = this.stockEntry.id;
                    this.move.type = this.stockEntry.type;
                    return;
                }
                this.$ajax.request(Api.move.getById, {
                    id: this.editId
                }).then(resp => {
                    this.move = resp;
                })
            },

            clickSubmit() {
                let api = this.editId ? Api.move.update : Api.move.insert;
                this.$ajax.request(api, this.move).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                    this.$emit('finish');
                })
            },
            warehouseChange(nodes) {
                this.move.warehouseId = nodes[0];
                this.move.reservoirId = nodes[1];
                this.move.stockId = nodes[2];
            }
        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                }
            }
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

    .MoveAddDialog {
    }
</style>