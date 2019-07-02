<template>
    <div class="CompanyAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="500px"
                :title=" editId? `编辑 ${editId}` : '新增'"
                :close-on-click-modal="false"
        >
            <el-form
                    v-loading="loading"
            >
                <el-row :gutter="5">
                    <el-col :span="18">
                        <el-form-item label="编码" :required="true">
                            <el-input type="text" v-model="company.code"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="分类" :required="true">
                            <company-category-selector
                                    v-model="company.category"
                                    :is-filter="false"
                            ></company-category-selector>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="16">
                        <el-form-item label="公司名称" :required="true">
                            <el-input type="text" v-model="company.name"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="联系人名称" :required="true">
                            <el-input type="text" v-model="company.contactName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="18">
                        <el-form-item label="公司地址" :required="true">
                            <el-input type="text" v-model="company.address"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="联系人手机号" :required="true">
                            <el-input type="text" v-model="company.mobile"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="座机号">
                            <el-input type="text" v-model="company.phone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="Email">
                            <el-input type="text" v-model="company.email"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="24">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="company.remark"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div class="text-right">
                <el-button-submit
                        :is-block="false"
                        @click.native="submit"
                >保存提交
                </el-button-submit>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import CompanyModel from "@/project/model/CompanyModel";
    import CompanyCategorySelector from "@/components/page-content/company/comp/CompanyCategorySelector";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import CompanySelector from "@/components/page-content/enum-selector/CompanySelector";

    export default {
        name: "CompanyAddDialog",
        components: {CompanySelector, ElButtonSubmit, CompanyCategorySelector},
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
                company: new CompanyModel(),
                loading: false
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            showDialog(v) {
                this.$emit('update:visible', v)
            },
            refreshCompany() {
                if (!this.editId) {
                    this.loading = false;
                    return this.company = new CompanyModel;
                }
                this.loading = true;
                this.$ajax.request(Api.company.getById, {
                    id: this.editId
                }).then(resp => {
                    this.company = resp;
                }).finally(() => this.loading = false)
            },
            submit() {
                let api = this.editId ? Api.company.edit : Api.company.add;
                this.$ajax.request(api, this.company)
                    .then(resp => {
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                        this.$emit('finish');
                    })
            }
        },
        watch: {
            visible(v) {
                if (v) this.refreshCompany();
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CompanyAddDialog {
    }
</style>