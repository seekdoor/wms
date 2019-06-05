<template>
    <div class="RoleManagerView flex-box flex-column">
        <search-card-layout
                :default-show="false"
                title="角色查询与操作"
                @click-search="refreshData"
        >
            <div class="flex-box flex-center search-card">
                <div class="">
                    <span>角色名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
            </div>
            <div class="" slot="buttons">
                <el-button-curd-group
                        :delete-disabled="!selectedRow.length"
                        :edit-disabled="selectedRow.length !== 1"
                        @click-add="clickAddButton"
                        @click-edit="clickEditButton"
                        @click-delete="clickDeleteButton"
                ></el-button-curd-group>
            </div>
        </search-card-layout>

        <div class="flex-grow mb-xs">
            <div class="flex-box h100p ">
                <div class="flex-grow overflow-h ">
                    <table-panel
                            :loading="loading"
                            :data="data"
                            :paginate="paginate"
                            operate-width="80"
                            :selectedRow.sync="selectedRow"
                            @sort-change="sortChange"
                            :create-user-show="true"
                            :update-user-show="true"
                    >
                        <el-table-column
                                prop="id"
                                label="ID"
                                sortable="custom"
                                width="80"
                        ></el-table-column>
                        <el-table-column
                                prop="name"
                                label="角色名称"
                                sortable="custom"
                                min-width="140"
                        ></el-table-column>

                        <template slot="operate" slot-scope="{row}">
                            <el-button-mini type="text" icon="edit"
                                            @click.native="clickRowEditButton(row)"></el-button-mini>
                            <el-button-mini type="text" icon="delete"
                                            @click.native="clickRowDeleteButton(row)"></el-button-mini>
                        </template>
                    </table-panel>
                </div>
                <div class="flex-shrink-off ml-xs  " style="width: 300px;max-height: 100%;">
                    <window-card-layout title="权限编辑">
                        <auth-manager-content></auth-manager-content>
                    </window-card-layout>
                </div>
            </div>
            <role-add-dialog
                    :visible.sync="showAddDialog"
                    :edit-id="editId"
                    @finish="addDialogFinish"
            ></role-add-dialog>
        </div>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import RoleModel from "@/project/model/RoleModel";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import TablePanel from "@/components/layout-component/TablePanel";
    import PaginateModel from "@/project/model/PaginateModel";
    import Api from "@/assets/api/Api";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import AuthManagerContent from "@/components/page-content/user/content/AuthManagerContent";
    import WindowCardLayout from "@/components/layout-component/WindowCardLayout";
    import RoleAddDialog from "@/components/page-content/user/dialog/RoleAddDialog";
    import EcUtil from "@/util/EcUtil";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "RoleManagerView",
        components: {
            RoleAddDialog,
            WindowCardLayout, AuthManagerContent, ElButtonMini, TablePanel, ElButtonCurdGroup, SearchCardLayout
        },
        props: {},
        data() {
            return {
                loading: true,
                showAddDialog: false,
                filterForm: new RoleModel(),
                data: [],
                paginate: new PaginateModel(this.refreshData),
                selectedRow: [],
                sortingColumn: null,
                editId: 0,
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
                this.loading = true;
                this.$ajax.request(Api.role_auth.roleList, {
                    ...this.paginate.getJSON(),
                    ...this.filterForm,
                    ...EcUtil.getSortField(this.sortingColumn)
                }).then(resp => {
                    this.data = resp.list;
                    this.paginate.setPaginate(resp);
                }).finally(() => this.loading = false)
            },
            clickAddButton() {
                this.editId = 0;
                this.showAddDialog = true;
            },
            clickEditButton() {
                this.editId = this.selectedRow[0].id;
                this.showAddDialog = true;
            },
            clickDeleteButton() {
                this.delete(this.selectedRow);
            },
            clickRowEditButton(row) {
                this.editId = row.id;
                this.showAddDialog = true;
            },
            clickRowDeleteButton(row) {
                this.delete([row]);
            },
            delete(nodes) {
                DialogUtil.confirm(`
                    确定删除以下角色？ <br>
                    [ ${nodes.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.role_auth.roleDelete, {
                        ids: nodes.map(x => x.id).join(',')
                    });
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },
            sortChange(column) {
                this.sortingColumn = column;
                this.$nextTick(() => this.refreshData());
            },

            addDialogFinish() {
                this.editId = 0;
                this.refreshData();
            }
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .RoleManagerView {
    }
</style>