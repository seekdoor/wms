<template>
    <div class="SearchInput">
        <form>
            <el-input :placeholder="placeholder"
                      v-model="searchText"
                      :style="inputStyle"
                      class="input-with-select">
                <el-button slot="append"
                           icon="el-icon-search color-blue"
                           type=""
                           native-type="submit"
                           @click.native="$emit('click-search')"
                           :loading="loading"

                ><span class="color-blue">{{ buttonText }}</span>
                </el-button>
            </el-input>
        </form>
    </div>
</template>

<script>
    export default {
        name: "SearchInput",
        components: {},
        props: {
            value: {
                default: ''
            },
            buttonText: {
                default: '查找'
            },
            placeholder: {
                default: '名称、接口地址'
            },
            loading: {
                default: false
            },
            width: {
                default: '340px'
            }
        },
        data() {
            return {
                searchText: this.value
            }
        }
        ,
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
        },
        watch: {
            searchText(v) {
                this.$emit('input', v);
            },
        },
        computed: {
            inputStyle() {
                return {
                    width: this.width
                }
            }
        },
    }
</script>

<style lang="less" scoped>
    @import "~style/var.less";

    .SearchInput {
    }
</style>