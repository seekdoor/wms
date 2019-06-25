<template>
    <div class="StockEntryAddDialog">
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
                            <el-input :disabled="true" :value="stockEntry.number" placeholder="单号将由系统自动生成"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`订单号`">
                            <el-input v-model="stockEntry.orderNumber"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="10">
                        <el-form-item :label="`${titleText}分类`">
                            <category-selector
                                    v-model="stockEntry.categoryId"
                                    :type="categoryType"
                                    :is-filter="false"
                            ></category-selector>
                        </el-form-item>
                    </el-col>
                    <el-col :span="14">
                        <el-form-item :label="`${companyText}`">
                            <company-selector
                                    v-model="stockEntry.companyId"
                                    :category="companyType"
                                    :is-filter="false"
                            ></company-selector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`备注`">
                            <el-input type="textarea" v-model="stockEntry.remark"></el-input>
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

    export default {
        name: "StockEntryAddDialog",
        components: {ElButtonSubmit, CompanySelector, CategorySelector},
        props: {
            type: {
                default: 1
            },
            visible: {
                default: false
            },
            editId: {
                default: 0,
            }
        },
        data() {
            return {
                stockEntry: new StockEntryModel(this.type),

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
                    this.stockEntry = new StockEntryModel(this.type);
                    return;
                }
                this.$ajax.request(Api.stockEntry.getById, {
                    id: this.editId
                }).then(resp => {
                    this.stockEntry = resp;
                })
            },

            clickSubmit() {
                let api = this.editId ? Api.stockEntry.update : Api.stockEntry.insert;
                this.$ajax.request(api, this.stockEntry).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                    this.$emit('finish');
                })
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
                return this.type === 1 ? '入库' : '出库';
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

    .StockEntryAddDialog {
    }
</style>