<template>
    <div class="AuthManagerContent overflow-h">
        <div class="" v-if="!isSelect">
            <el-button-curd-group
                    :edit-disabled="selectedNodes.length !== 1"
                    :delete-disabled="selectedNodes.length < 1"
                    @click-add="clickAddButton"
                    @click-edit="clickEditButton"
                    @click-delete="clickDeleteButton"
            ></el-button-curd-group>
        </div>
        <div class="mt-xs" style="max-height: 400px;overflow-y:auto;">
            <el-tree
                    :data="dataTree"
                    show-checkbox
                    default-expand-all
                    :expand-on-click-node="false"
                    node-key="id"
                    ref="tree"
                    highlight-current
                    :props="defaultProps"
                    @check-change="checkChange"
            >
                <div class="flex-box flex-center fz-xs w100p" slot-scope="{ node, data }">
                    <div class="flex-grow">{{ node.label }}</div>
                    <div v-if="!isSelect">
                        <el-button-mini type="text" icon="add" v-if="data.pid === 0"
                                        @click.native="clickRowAddButton(data)"></el-button-mini>
                        <el-button-mini type="text" icon="edit"
                                        @click.native="clickRowEditButton(data)"></el-button-mini>
                        <el-button-mini type="text" icon="delete"
                                        @click.native="clickRowDeleteButton(data)"></el-button-mini>
                    </div>
                </div>
            </el-tree>
        </div>
        <auth-add-dialog
                :visible.sync="showAddDialog"
                :edit-id="editId"
                :edit-pid="editPid"
                @finish="addDialogFinish"
        ></auth-add-dialog>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";
    import CollectionUtil from "@/util/CollectionUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import DebThrUtil from "@/util/DebThrUtil";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import AuthAddDialog from "@/components/page-content/user/dialog/AuthAddDialog";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "AuthManagerContent",
        components: {AuthAddDialog, ElButtonCurdGroup, ElButtonMini},
        props: {
            isSelect: {
                default: false,
            },
        },
        data() {
            return {
                showAddDialog: false,
                data: [],
                defaultProps: {
                    children: 'children',
                    label: 'name',
                },
                form: {
                    name: ''
                },
                selectedNodes: [],
                editId: 0,
                editPid: 0,
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
            },
            checkChange() {
                DebThrUtil.debounce('checkChange' + this.uid, () => {
                    this.selectedNodes = this.$refs.tree.getCheckedNodes();
                    this.$emit('check-change', this.selectedNodes);
                });
            },
            clickAddButton() {
                this.editId = 0;
                this.showAddDialog = true;
            },
            clickEditButton() {
                this.editId = this.selectedNodes[0].id;
                this.showAddDialog = true;
            },
            clickDeleteButton() {
                this.delete(this.selectedNodes);
            },
            clickRowDeleteButton(row) {
                this.delete([row]);
            },
            clickRowAddButton(row) {
                this.editPid = row.id;
                this.showAddDialog = true;
            },
            clickRowEditButton(row) {
                this.editId = row.id;
                this.showAddDialog = true;
            },
            delete(nodes) {
                DialogUtil.confirm(`
                    确定要删除以下权限吗？ <br>
                    [ ${nodes.map(x => x.name)} ]
                `).then(() => {
                    return this.$ajax.request(Api.role_auth.authDelete, {
                        ids: nodes.map(x => x.id).join(',')
                    })
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },
            addDialogFinish() {
                this.refreshData();
                this.editPid = 0;
                this.editId = 0;
            },
            setCheckedKeys(ids) {
                this.$refs.tree.setCheckedKeys(ids);
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