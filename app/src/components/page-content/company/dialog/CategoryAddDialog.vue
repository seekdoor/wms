<template>
    <div class="CategoryAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId? `编辑 ${category.id}` : `新增`"
        >
            <el-form label-position="top">
                <el-form-item label="字典名称">
                    <el-input type="text" v-model="category.name"></el-input>
                </el-form-item>
                <el-form-item label="字典分类">
                    <category-type-selector
                            v-model="category.type"
                            :is-filter="false"
                    ></category-type-selector>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="category.remark"></el-input>
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
    import CategoryModel from "@/project/model/CategoryModel";
    import CategoryTypeSelector from "@/components/page-content/enum-selector/CategoryTypeSelector";

    export default {
        name: "CategoryAddDialog",
        components: {CategoryTypeSelector, ElButtonSubmit},
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
                category: new CategoryModel(),
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
                    return this.category = new CategoryModel;
                }
                this.loading = true;
                this.$ajax.request(Api.category.getById, {
                    id: this.editId,
                }).then(resp => {
                    this.category = resp;
                }).finally(() => this.loading = false)
            },
            clickSubmit() {
                let api = this.editId ? Api.category.update : Api.category.insert;
                this.loading = true;
                this.$ajax.request(api, this.category)
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

    .CategoryAddDialog {
    }
</style>