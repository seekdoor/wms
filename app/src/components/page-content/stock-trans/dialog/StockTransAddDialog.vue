<template>
    <div class="StockTransAddDialog">
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
                        <el-form-item :label="`${titleText}单`">
                            <el-input :disabled="true" :value="stockTrans.number" placeholder="单号将由系统自动生成"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`源货架`" :required="true">
                            <warehouse-selector
                                    width="100%"
                                    :level="3"
                                    :value="stockTrans.stockId"
                                    :need-filter="true"
                                    :filter-ids="stockFilters"
                                    @change="changeStock"
                            ></warehouse-selector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`目标货架`" :required="true">
                            <warehouse-selector
                                    width="100%"
                                    :level="3"
                                    :value="stockTrans.stockTid"
                                    @change="changeStockTo"
                            ></warehouse-selector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`备注`">
                            <el-input type="textarea" v-model="stockTrans.remark"></el-input>
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
    import StockTransModel from "@/project/model/StockTransModel";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import MaterialSelector from "@/components/page-content/enum-selector/MaterialSelector";

    export default {
        name: "StockTransAddDialog",
        components: {MaterialSelector, WarehouseSelector, ElButtonSubmit, CompanySelector, CategorySelector},
        props: {
            visible: {
                default: false
            },
            editId: {
                default: 0,
            }
        },
        data() {
            return {
                stockTrans: new StockTransModel(),
                stockFilters: [],
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
                this.refreshFilters();
                if (!this.editId) {
                    this.stockTrans = new StockEntryModel(this.type);
                    return;
                }
                this.$ajax.request(Api.stockTrans.getById, {
                    id: this.editId
                }).then(resp => {
                    this.stockTrans = resp;
                })
            },

            refreshFilters() {
                this.$ajax.request(Api.inventory.selectAllStockIds).then(resp => {
                    this.stockFilters = resp;
                })
            },


            clickSubmit() {
                let api = this.editId ? Api.stockTrans.update : Api.stockTrans.insert;
                this.$ajax.request(api, this.stockTrans).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                    this.$emit('finish');
                })
            },
            changeStock(nodes) {
                this.stockTrans.warehouseId = nodes[0];
                this.stockTrans.reservoirId = nodes[1];
                this.stockTrans.stockId = nodes[2];
            },
            changeStockTo(nodes) {
                this.stockTrans.warehouseTid = nodes[0];
                this.stockTrans.reservoirTid = nodes[1];
                this.stockTrans.stockTid = nodes[2];
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
                return '移位';
            },
            categoryType() {
                return this.type === 1 ? 3 : 4;
            },
            title() {
                return this.editId ? `编辑 ${this.titleText} ${this.editId}` : `新增 ${this.titleText}`
            },
            companyText() {
                return this.type === 1 ? '供应商' : '客户';
            },
            companyType() {
                return this.type === 1 ? 1 : 2;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockTransAddDialog {
    }
</style>