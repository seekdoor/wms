<template>
    <div class="AuthAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :title="editId?`编辑 ${auth.id}`:'新增'"
        >
            <el-form label-position="top">
                <el-form-item label="权限名称">
                    <el-input type="text" v-model="auth.name"></el-input>
                </el-form-item>
                <el-form-item label="权限所属">
                    <el-select v-model="auth.pid">
                        <el-option label="顶级分类" :value="0"></el-option>
                        <el-option
                                v-for="item in authList"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="权限地址">
                    <el-input type="text" v-model="auth.url"></el-input>
                </el-form-item>
            </el-form>
            <el-button-submit
                    size="small"
                    style="margin-top: 20px;"
                    @click.native="clickSubmitButton"
                    :loading="submitting"
            >保存提交
            </el-button-submit>
        </el-dialog>
    </div>
</template>

<script>
    import AuthModel from "@/project/model/AuthModel";
    import Api from "@/assets/api/Api";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "AuthAddDialog",
        components: {ElButtonSubmit},
        props: {
            visible: {
                default: false
            },
            editId: {
                default: 0
            },
            editPid: {
                default: 0
            }
        },
        data() {
            return {
                auth: new AuthModel(),
                authList: [],
                submitting: false,
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
                if (this.editId === 0) {
                    this.auth = new AuthModel;
                    this.auth.pid = this.editPid;
                    return;
                }
                this.$ajax.request(Api.role_auth.getAuthById, {
                    id: this.editId
                }).then(resp => {
                    this.auth = resp;
                });
            },
            refreshAuthList() {
                this.$ajax.request(Api.role_auth.authAll, {
                    pid: 0
                }).then(resp => {
                    this.authList = resp.filter(x => x.id !== this.editId);
                });
            },
            clickSubmitButton() {
                this.submitting = true;
                let api = this.editId ? Api.role_auth.authUpdate : Api.role_auth.authInsert;
                this.$ajax.request(api, this.auth)
                    .then(resp => {
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                        this.$emit('finish');
                    }).finally(() => this.submitting = false)
            }
        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                    this.refreshAuthList();
                }
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .AuthAddDialog {
    }
</style>