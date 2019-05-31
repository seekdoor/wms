<template>
    <div class="AuthManagerContent overflow-h" >
        <div class="" style="max-height: 500px;overflow-y:auto;" >
            <el-tree
                    :data="dataTree"
                    show-checkbox
                    default-expand-all
                    :expand-on-click-node="false"
                    node-key="id"
                    ref="tree"
                    highlight-current
                    :props="defaultProps">
                <div class="flex-box flex-center fz-xs w100p" slot-scope="{ node, data }">
                    <div class="flex-grow">{{ node.label }}</div>
                    <div>
                        <el-button-mini type="text" icon="add"></el-button-mini>
                        <el-button-mini type="text" icon="edit"></el-button-mini>
                        <el-button-mini type="text" icon="delete"></el-button-mini>
                    </div>
                </div>
            </el-tree>

        </div>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";
    import CollectionUtil from "@/util/CollectionUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";

    export default {
        name: "AuthManagerContent",
        components: {ElButtonMini},
        props: {},
        data() {
            return {
                data: [],
                defaultProps: {
                    children: 'children',
                    label: 'name',
                },
                form: {
                    name: ''
                }
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshData();
            },
            refreshData() {
                this.$ajax.request(Api.role_auth.authAll, this.form)
                    .then(resp => {
                        this.data = resp;
                    });
            }
        },
        watch: {},
        computed: {
            dataTree() {
                return CollectionUtil.listToTree(this.data);
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .AuthManagerContent {
    }
</style>