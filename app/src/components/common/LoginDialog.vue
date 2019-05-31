<template>
    <div class="LoginDialog">
        <el-dialog
                title="后台登陆"
                :visible.sync="visible"
                :show-close="false"
                :close-on-click-modal="false"
                width="320px"
        >
            <el-form :model="form">
                <el-form-item label="用户名">
                    <el-input v-model="form.userName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
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
        </el-dialog>
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
                console.log(123);
            },
            clickSubmitButton() {
                this.submitting = true;
                AjaxUtil.request(Api.logReg.login, this.form)
                    .then(user => {
                        UserHandle.setLoginUser(user);
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
    }
</style>