<template>
    <div class="LoginDialog">
        <div class="mar-center login-dialog" >
            <div class="fz-md text-center dialog-title">WMS 登录</div>
            <el-form :model="form">
                <el-form-item label="用户名">
                    <el-input v-model="form.userName" autocomplete="off" size="middle"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="form.password" autocomplete="off" size="middle"></el-input>
                </el-form-item>
            </el-form>
            <div class="mt-lg">
                <el-button
                        class=""
                        style="width: 100%;"
                        size="large"
                        type="primary"
                        @click.native="clickSubmitButton"
                        :loading="submitting"
                        icon="iconfont icon-unlock-alt"
                >
                    登录后台
                </el-button>
            </div>
        </div>
    </div>
</template>

<script>
    import DialogUtil from "@/util/DialogUtil";
    import LoadingUtil from "@/util/LoadingUtil";
    import AjaxUtil from "@/util/AjaxUtil";
    import Api from "@/assets/api/Api";
    import UserHandle from "@/project/user/UserHandle";
    import RouterUtil from "@/util/RouterUtil";

    export default {
        name: "LoginDialog",
        components: {},
        props: {
            visible: {
                default: false
            }
        },
        data() {
            return {
                formLabelWidth: 100,
                submitting: false,
                form: {
                    userName: 'admin',
                    password: '123456',
                }
            }
        },
        mounted() {
            this.reload();
        },
        activated() {
        },
        methods: {
            reload() {
            },
            clickSubmitButton() {
                this.submitting = true;
                AjaxUtil.request(Api.logReg.login, this.form)
                    .then(user => {
                        UserHandle.setLoginUser(user);
                        DialogUtil.toastSuccess("成功登陆后台");
                        this.$_router.to('/Back');
                    }).finally(() => this.submitting = false);
            }
        },
        watch: {},
        computed: {},
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .LoginDialog {
        .login-dialog{
            width: 260px;
            margin-top: 100px;
            padding: 20px 20px;
            border: 1px solid #eaeaea;
        }
        .dialog-title{

        }
    }
</style>