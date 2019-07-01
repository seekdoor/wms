<template>
    <div class="HeaderNav flex-box flex-center h100p lh-1 pl-md pr-md bg-white">
        <div class="pr-sm color-navy"
        >
            <i class="iconfont icon-arrow-left cursor-p disp-ib trans"
               :class="menuArrowClass"
               @click="clickMenuArrow"
            ></i>
        </div>
        <div class="fz-sm">
            <el-button circle type="danger" icon="iconfont icon-lightbulb" title="最近发生的错误"
                       @click.native="clickErrorButton"
                       v-if="user.isRoot === 1"
            ></el-button>
        </div>
        <div class="flex-grow"></div>
        <div class="fz-xs mr-sm">
            你好，
            <span class="cursor-p"
                  style="text-decoration: underline"
                  @click="showEditMyInfoDialog = true"
            >
                {{ user.realName }} ( {{ user.userName }} )
            </span>。
        </div>
        <div class="fz-xs">
            <el-button-mini
                    type="text"
                    icon="iconfont icon-sign-out-alt"
                    @click.native="logout"
            ><span class="color-black">退出登录</span>
            </el-button-mini>
        </div>
        <my-info-edit-dialog
                :visible.sync="showEditMyInfoDialog"
        ></my-info-edit-dialog>
    </div>
</template>

<script>
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import StatesContainer from "@/project/StatesContainer";
    import RouterUtil from "@/util/RouterUtil";
    import UserHandle from "@/project/user/UserHandle";
    import Api from "@/assets/api/Api";
    import DialogUtil from "@/util/DialogUtil";
    import MyInfoEditDialog from "@/components/common/dialog/MyInfoEditDialog";
    import Windows from "@/project/var/Windows";

    export default {
        name: "HeaderNav",
        components: {MyInfoEditDialog, ElButtonMini},
        props: {},
        data() {
            return {
                states: StatesContainer,
                user: UserHandle.getLoginUser(),
                showEditMyInfoDialog: false
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
            clickMenuArrow() {
                this.$store.commit('setAsideIsExpend', !this.$store.state.asideIsExpend);
            },
            clickErrorButton() {
                this.states.layout.errorWindowVisible = true;
            },
            logout() {
                DialogUtil.confirm("确定退出登录？").then(() => {
                    return this.$ajax.request(Api.user.logout);
                }).then(resp => {
                    Windows.clearWindow();
                    DialogUtil.toastSuccess(resp);
                    RouterUtil.toLogin();
                })
            }
        },
        watch: {},
        computed: {
            menuArrowClass() {
                return {
                    'arrow-right': !this.$store.state.asideIsExpend
                }
            }
        },
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .HeaderNav {
        .arrow-right {
            transform: rotateY(180deg);
        }
    }
</style>