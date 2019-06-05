<template>
    <div class="DepartManagerView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <div class="flex-box search-card">
                <div class="" style="width: 300px;">
                    <span>部门编码</span>
                    <el-input></el-input>
                </div>
                <div class="" style="width: 230px;">
                    <span>部门名称</span>
                    <el-input></el-input>
                </div>
            </div>
            <el-button-curd-group
                    slot="buttons"
                    :delete-disabled="!selectedRow.length"
                    :edit-disabled="selectedRow.length !== 1"
                    @click-add="clickAddButton"
                    @click-edit="clickEditButton"
                    @click-delete="clickDeleteButton"
            ></el-button-curd-group>
        </search-card-layout>
        <div class="flex-grow">
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
                        prop="code"
                        label="部门编码"
                        sortable="custom"
                        width="180"
                ></el-table-column>
                <el-table-column
                        prop="name"
                        label="部门名称"
                        sortable="custom"
                        width="180"
                ></el-table-column>
                <template slot="operate" slot-scope="{row}">
                    <el-button-mini
                            type="text"
                            icon="edit"
                            @click.native="clickRowEditButton(row)"
                    ></el-button-mini>
                    <el-button-mini
                            type="text"
                            icon="delete"
                            @click.native="clickRowDeleteButton(row)"
                    ></el-button-mini>
                </template>
            </table-panel>
        </div>
        <depart-add-dialog
                :visible.sync="showAddDialog"
                @finish="refreshData"
                :edit-id="editId"
        ></depart-add-dialog>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import TablePanel from "@/components/layout-component/TablePanel";
    import PaginateModel from "@/project/model/PaginateModel";
    import DepartModel from "@/project/model/DepartModel";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import DepartAddDialog from "@/components/page-content/user/dialog/DepartAddDialog";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "DepartManagerView",
        components: {DepartAddDialog, ElButtonMini, TablePanel, ElButtonCurdGroup, SearchCardLayout},
        props: {},
        data() {
            return {
                loading: true,
                showAddDialog: false,
                filterForm: new DepartModel(),
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
                this.$ajax.request(Api.depart.list, {
                    ...EcUtil.getSortField(this.sortingColumn),
                    ...this.paginate.getJSON(),
                    ...this.filterForm
                }).then(resp => {
                    this.data = resp.list;
                    this.paginate.setPaginate(resp);
                }).finally(() => this.loading = false)
            },

            sortChange(nodes) {
                this.sortingColumn = nodes;
                this.refreshData();
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
                    确定删除以下部门吗？ <br>
                    [ ${nodes.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.depart.delete, {
                        ids: nodes.map(x => x.id).join(',')
                    });
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },

        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DepartManagerView {
    }
</style>