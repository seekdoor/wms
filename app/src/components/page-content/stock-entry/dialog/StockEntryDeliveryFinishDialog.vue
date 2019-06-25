<template>
    <div class="StockEntryDeliveryFinishDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="400px"
                :title="`完成发货`"
        >
            <el-form
                    label-position="top"
            >
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item label="订单号">
                            <el-input :value="form.stockEntry.number" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item class="text-center">
                            <el-radio v-model="form.needDelivery" :label="1">需要物流</el-radio>
                            <el-radio v-model="form.needDelivery" :label="0">无需物流</el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
                <template v-if="form.needDelivery === 1">
                    <el-row :gutter="10">
                        <el-col :span="24">
                            <el-form-item label="快递单号" :required="true">
                                <el-input v-model="form.delivery.number"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="14">
                            <el-form-item label="承运商" :required="true">
                                <company-selector
                                        v-model="form.delivery.companyId"
                                        :is-filter="false"
                                        :category="3"
                                ></company-selector>
                            </el-form-item>
                        </el-col>
                        <el-col :span="10">
                            <el-form-item label="发货日期" :required="true">
                                <el-date-picker
                                        style="width: 100%;"
                                        type="date"
                                        v-model="form.delivery.happenTime"
                                        :clearable="false"
                                ></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="10">
                        <el-col :span="24">
                            <el-form-item label="备注" :required="false">
                                <el-input type="textarea" v-model="form.delivery.remark"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </template>
            </el-form>

            <div class="text-right mt-sm">
                <el-button-submit
                        :is-block="false"
                        :loading="loading"
                        @click.native="submit"
                >完成发货
                </el-button-submit>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import StockEntryModel from "@/project/model/StockEntryModel";
    import CompanySelector from "@/components/page-content/enum-selector/CompanySelector";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import StockEntryDeliveryFinishFormModel from "@/project/model/StockEntryDeliveryFinishFormModel";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "StockEntryDeliveryFinishDialog",
        components: {ElButtonSubmit, CompanySelector},
        props: {
            visible: {
                default: false
            },
            stockEntry: {
                default() {
                    return new StockEntryModel()
                }
            }
        },
        data() {
            return {
                loading: false,
                form: new StockEntryDeliveryFinishFormModel()
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
            submit() {
                this.loading = true;
                this.$ajax.request(Api.stockEntry.deliveryFinish, this.form).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                    this.$emit('finish');
                }).finally(() => this.loading = false);
            }
        },
        watch: {
            visible(v) {
                if (v) {
                    this.form = new StockEntryDeliveryFinishFormModel;
                    this.form.stockEntry = Object.assign({}, this.stockEntry);
                }
            } ,
            'form.needDelivery' (v){
                console.log(v);
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockEntryDeliveryFinishDialog {
    }
</style>