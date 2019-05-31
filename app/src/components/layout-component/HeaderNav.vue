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
            ></el-button>
        </div>
        <div class="flex-grow"></div>
        <div class="fz-sm">
            退出登录
        </div>
    </div>
</template>

<script>
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import StatesContainer from "@/project/StatesContainer";

    export default {
        name: "HeaderNav",
        components: {ElButtonMini},
        props: {},
        data() {
            return {
                states: StatesContainer,
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