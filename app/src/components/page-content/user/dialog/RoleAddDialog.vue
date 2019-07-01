<template>
    <div class="RoleAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="320px"
                :title=" editId ? `编辑 ${editId}` : '新增' "
        >
            <el-form label-position="top">
                <el-form-item label="角色名称">
                    <el-input type="text" v-model="role.name"></el-input>
                </el-form-item>
                <el-form-item label="选择权限">
                    <auth-manager-content
                            :is-select="true"
                            ref="tree"
                            @check-change="checkChange"
                    ></auth-manager-content>
                </el-form-item>
            </el-form>
            <el-button-submit
                    class="mt-md"
                    size="small"
                    @click.native="clickSubmitButton"
            >保存提交
            </el-button-submit>
        </el-dialog>
    </div>
</template>

<script>
    import RoleModel from "@/project/model/RoleModel";
    import AuthManagerContent from "@/components/page-content/user/content/AuthManagerContent";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "RoleAddDialog",
        components: {ElButtonSubmit, AuthManagerContent},
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
                role: new RoleModel()
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
                    this.role = new RoleModel;
                    this.$refs.tree.setCheckedKeys([]);
                    return;
                }
                this.$ajax.request(Api.role_auth.getRoleById, {
                    id: this.editId
                }).then(resp => {
                    this.role = resp;
                    this.role.auths = this.role.auths || [];
                    this.$refs.tree.setCheckedKeys(this.role.auths.map(x => x.id));
                })
            },
            clickSubmitButton() {
                let api = this.editId ? Api.role_auth.roleUpdate : Api.role_auth.roleInsert;
                this.$ajax.request(api, this.role)
                    .then(resp => {
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                        this.$emit('finish');
                    });
            },
            checkChange(nodes) {
                this.role.auths = nodes;
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

    .RoleAddDialog {
    }
</style>