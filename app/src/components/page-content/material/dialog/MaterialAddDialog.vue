<template>
    <div class="MaterialAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${material.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`${nameText}编号`" :required="true">
                            <el-input type="text" v-model="material.code"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="24">
                        <el-form-item :label="`${nameText}名称`" :required="true">
                            <el-input type="text" v-model="material.name"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="15">
                        <el-form-item :label="`${nameText}分类`" :required="true">
                            <category-selector
                                    :type="categoryType"
                                    v-model="material.categoryId"
                                    :is-filter="false"
                            ></category-selector>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="单位" :required="true">
                            <category-selector
                                    :type="1"
                                    v-model="material.unitId"
                                    :is-filter="false"
                            ></category-selector>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="安全库存" :required="true">
                            <el-input-integer v-model="material.safeNum"></el-input-integer>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item :required="true">
                            <template class="" slot="label">
                                有效天数
                                <el-tooltip effect="dark" content="0 代表该物料永久有效" placement="top-start">
                                    <i class="iconfont icon-exclamation-circle cursor-p"></i>
                                </el-tooltip>
                            </template>
                            <el-input v-model="material.validDay"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>


                <el-form-item label="备注">
                    <el-input type="textarea" v-model="material.remark"></el-input>
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
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import MaterialModel from "@/project/model/MaterialModel";
    import CategoryTypeSelector from "@/components/page-content/enum-selector/CategoryTypeSelector";
    import CategorySelector from "@/components/page-content/enum-selector/CategorySelector";
    import ElInputInteger from "@/components/common/input/ElInpuInteger";

    export default {
        name: "MaterialAddDialog",
        components: {ElInputInteger, CategorySelector, CategoryTypeSelector, WarehouseSelector, ElButtonSubmit},
        props: {
            visible: {
                default: false
            },
            editId: {
                default: 0
            },
            categoryType: {
                default: 2
            },
            type: {
                default: 1
            }
        },
        data() {
            return {
                material: new MaterialModel(),
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
                    return this.material = new MaterialModel();
                }
                this.loading = true;
                this.$ajax.request(Api.material.getById, {
                    id: this.editId,
                }).then(resp => {
                    this.material = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.material.update : Api.material.insert;
                this.loading = true;
                this.material.type = this.type;
                this.$ajax.request(api, this.material)
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
            },
            'material.safeNum'(v) {
                let c = (v + '').match(/[1-9]\d*/);
                this.material.safeNum = c ? c[0] : 0;
            },
            'material.validDay'(v) {
                let c = (v + '').match(/[1-9]\d*/);
                this.material.validDay = c ? c[0] : 0;
            }
        },
        computed: {
            nameText() {
                return this.categoryType === 2 ? '物料' : '产品';
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MaterialAddDialog {
    }
</style>