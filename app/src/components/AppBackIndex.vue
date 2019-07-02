<template>
    <div class="AppBackIndex flex-box h100p ">
        <div class="flex-box flex-column overflow-h flex-shrink-off h100p trans "
             :style="leftContainerStyle"
        >
            <div class="layout-title flex-box flex-shrink-off flex-center color-silver overflow-h">
                <span v-if="$store.state.asideIsExpend">通用 WMS 系统</span>
                <i class="iconfont icon-node-js " v-if="!$store.state.asideIsExpend"></i>
            </div>
            <div class="layout-aside-menu flex-grow">
                <aside-menu></aside-menu>
            </div>
        </div>
        <div class="flex-box flex-grow flex-column overflow-h">
            <div class="layout-header flex-shrink-off shadow-gray">
                <header-nav></header-nav>
            </div>
            <div class="layout-content flex-grow">
                <div class="flex-box flex-column " style="height: 100%;">
                    <div class="flex-shrink-off" style="height: 5px;"></div>
                    <div class="flex-shrink-off" style="height: 41px;">
                        <el-tabs type="card"
                                 @edit="handleTabsEdit"
                                 v-model="windows.selectedName"
                        >
                            <el-tab-pane
                                    :closable="i!==0"
                                    :label="item.meta.title"
                                    v-for="(item,i) in windows.list"
                                    :key="item.path"
                                    :name="item.meta.title"
                            >
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                    <div class="flex-grow " style="overflow-y:auto;">
                        <div class="flex-box flex-column h100p">
                            <component
                                    v-for="item in windows.list"
                                    :is="item.component"
                                    :key="item.path"
                                    v-show="item.meta.title === windows.selectedName"
                                    class="flex-grow pa-xs "
                                    :params.sync="item.params || {}"
                            ></component>
                        </div>

                    </div>
                </div>
                <div class="layout-footer"></div>
            </div>
        </div>

    </div>
</template>

<script>
    import AsideMenu from "@/components/layout-component/AsideMenu";
    import HeaderNav from "@/components/layout-component/HeaderNav";
    import Windows from "@/project/var/Windows";

    export default {
        name: "AppBackIndex",
        components: {HeaderNav, AsideMenu},
        props: {},
        data() {
            return {
                windows: Windows
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
            handleTabsEdit(windowName) {
                Windows.remove(windowName)
            }
        },
        watch: {

        },
        computed: {
            leftContainerStyle() {
                return {
                    'width': this.$store.state.asideIsExpend ? '260px' : '60px',
                }
            },


        },
    }
</script>

<style lang="less" scoped>
    @import (reference) "~@/style/all.less";

    .AppBackIndex {
        width: 100%;
        height: 100%;
        overflow: hidden;

        .layout-header-height {
            height: 60px;
        }

        .left-container {
            .bg-navy;
        }

        .layout-title {
            .left-container;
            .layout-header-height;
            .pl-md;
            width: 260px;
        }

        .layout-header {
            .layout-header-height;
            z-index: 10;
            /*border-bottom: 1px solid #e0e0e0;*/
        }

        .layout-aside-menu {
            .left-container;
            overflow-x: hidden;
            overflow-y: scroll;

            &::-webkit-scrollbar {
                background-color: transparent;
                width: 5px;
            }

            &::-webkit-scrollbar-thumb {
                background-color: rgba(255, 255, 255, .2);
                border-radius: 3px;
            }

            &::-webkit-scrollbar-corner {
                background-color: transparent;
                height: 0;
            }
        }

        .layout-content {
            .pos-r;
            overflow: auto;
        }

        .layout-content-view {
            .pa-sm;

        }

        .layout-footer {
            .pos-f;
            height: 30px;
            bottom: 0;
            right: 0;
            left: 0;
        }
    }
</style>