<template>
    <div class="SearchCardLayout pb-xs">
        <div class="el-card box-card is-always-shadow">
            <div class="header flex-box flex-center el-card__header"
                 onselectstart="return false"
                 @dblclick="show = !show"
            >
                <div class="flex-grow">{{ title }}</div>
                <div class="trans" :class="showButtonClass">
                    <a href="javascript:;" class=""
                       @click="show = !show"
                    >
                        <i class="iconfont icon-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="body pl-sm pr-sm pt-xs " v-show="show">
                <slot></slot>
            </div>
            <div class="pl-sm pr-sm pt-xs pb-xs">
                <div class="flex-box flex-center">
                    <div class="flex-grow">
                        <slot name="buttons"></slot>
                    </div>
                    <div class="">
                        <el-button
                                icon="el-icon-search"
                                @click.native="$emit('click-search')"
                                :loading="loading"
                        >查询
                        </el-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import PaginateModel from "@/project/model/PaginateModel";

    export default {
        name: "SearchCardLayout",
        components: {ElButtonMini},
        props: {
            title: {
                default: '功能选项',
            },
            loading: {
                default: false
            },
            paginate: {
                default() {
                    return new PaginateModel()
                }
            },
            defaultShow :{
                default: true
            }
        },
        data() {
            return {
                show: this.defaultShow,
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
        },
        watch: {
            show(v) {
                this.$store.dispatch('setSearchShowHidden', v);
            }
        },
        computed: {
            showButtonClass() {
                return {
                    'show': !this.show
                }
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .SearchCardLayout {
        .show {
            transform: rotateZ(180deg);
        }
    }
</style>