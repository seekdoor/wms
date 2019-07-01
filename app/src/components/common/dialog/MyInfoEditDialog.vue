<template>
    <div class="MyInfoEditDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                width="300px"
                :modal-append-to-body="true"
                :append-to-body="true"
                title="修改密码"
        >
            <div class="fz-xs">
                <div class="">用户id: {{loginUser.id}}</div>
                <div class="">用户名: {{loginUser.userName}}</div>
                <div class="">真实名: {{loginUser.realName}}</div>
            </div>
            <el-form label-position="top">
                <el-form-item label="原密码">
                    <el-input v-model="user.oldPassword" type="password"></el-input>
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input v-model="user.password" type="password"></el-input>
                </el-form-item>
                <el-form-item label="旧密码">
                    <el-input v-model="user.rePassword" type="password"></el-input>
                </el-form-item>
            </el-form>
            <div class="mt-md">
                <el-button-submit
                        :loading="loading"
                        @click.native="submit"
                >保存提交
                </el-button-submit>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import UserModel from "@/project/model/UserModel";
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import UserHandle from "@/project/user/UserHandle";

    export default {
        name: "MyInfoEditDialog",
        components: {ElButtonSubmit},
        props: {
            visible: {
                default: false
            }
        },
        data() {
            return {
                user: new UserModel(),
                loginUser: UserHandle.getLoginUser(),
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
            submit() {
                this.user.id = this.loginUser.id;
                this.$ajax.request(Api.user.changeMyInfo, this.user).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.showDialog(false);
                })
            }
        },
        watch: {
            visible(v){
                if(v){
                    this.user = new UserModel;
                }
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MyInfoEditDialog {
    }
</style>