<template>
    <div class="UserAddDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="460px"
                :title="userId?`编辑 [ ${user.id} ]`:'新增'"
        >
            <el-form>

                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="用户名" required="">
                            <el-input type="text" v-model="user.userName"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="真实姓名" required="">
                            <el-input type="text" v-model="user.realName"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="手机号" required="">
                            <el-input type="text" v-model="user.mobile"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="座机">
                            <el-input type="text" v-model="user.phone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="Email">
                            <el-input type="text" v-model="user.email"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="用户角色" required>
                            <role-selector
                                    v-model="user.roleId"
                            ></role-selector>
                        </el-form-item>
                    </el-col>

                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="所在部门">
                            <depart-selector v-model="user.departId"></depart-selector>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="是否激活" required>
                            <el-select v-model="user.activated">
                                <el-option :value="1" label="暂不激活"></el-option>
                                <el-option :value="2" label="激活"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="5">
                    <el-col :span="12">
                        <el-form-item label="密码" :required="!userId">
                            <el-input type="password" v-model="user.password"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="重复密码" :required="!userId">
                            <el-input type="password" v-model="user.rePassword"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
            <div class="flex-box ">
                <div class="flex-grow"></div>
                <div class="">
                    <el-button-submit
                            @click.native="submit"
                    >保存提交
                    </el-button-submit>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import UserModel from "@/project/model/UserModel";
    import Api from "@/assets/api/Api";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import DialogUtil from "@/util/DialogUtil";
    import RoleSelector from "@/components/page-content/enum-selector/RoleSelector";
    import DepartSelector from "@/components/page-content/enum-selector/DepartSelector";

    export default {
        name: "UserAddDialog",
        components: {DepartSelector, RoleSelector, ElButtonSubmit},
        props: {
            visible: {
                default: false
            },
            userId: {
                default: 0,
            }
        },
        data() {
            return {
                user: new UserModel().setDefaultActivated()
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
            refreshUser() {
                if (!this.userId) {
                    this.user = new UserModel().setDefaultActivated();
                    return;
                }
                this.$ajax.request(Api.user.getUserById, {
                    id: this.userId
                }).then(resp => {
                    this.user = resp
                })
            },
            submit() {
                let api = this.userId ? Api.user.edit : Api.user.add;
                this.$ajax.request(api, this.user)
                    .then(resp => {
                        this.$emit('finish');
                        DialogUtil.toastSuccess(resp);
                        this.showDialog(false);
                    })
            }
        },
        watch: {
            visible(v) {
                if (v) this.refreshUser();
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .UserAddDialog {
    }
</style>