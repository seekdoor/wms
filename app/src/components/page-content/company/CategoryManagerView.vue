<template>
    <div class="CategoryManagerView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>字典名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
                <div class="" style="width: 180px;">
                    <span>字典分类</span>
                    <category-type-selector
                            v-model="filterForm.type"
                            :is-filter="true"
                    ></category-type-selector>
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
                        prop="name"
                        label="字典名称"
                        sortable="custom"
                        width="100"
                ></el-table-column>


                <el-table-column
                        prop="type"
                        label="字典分类"
                        sortable="custom"
                        width="100"
                >
                    <template slot-scope="{row}">{{ row.typeName }}</template>
                </el-table-column>


                <el-table-column
                        prop="remark"
                        label="备注"
                        sortable="custom"
                        width="200"
                        show-overflow-tooltip
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
        <category-add-dialog
                :visible.sync="showAddDialog"
                @finish="refreshData"
                :edit-id="editId"
        ></category-add-dialog>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import DialogUtil from "@/util/DialogUtil";
    import CategoryModel from "@/project/model/CategoryModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import CategoryTypeSelector from "@/components/page-content/enum-selector/CategoryTypeSelector";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";
    import CategoryAddDialog from "@/components/page-content/company/dialog/CategoryAddDialog";

    export default {
        name: "CategoryManagerView",
        components: {
            CategoryAddDialog,
            TablePanel, ElButtonMini, ElButtonCurdGroup, CategoryTypeSelector, SearchCardLayout
        },
        props: {},
        data() {
            return {
                loading: true,
                showAddDialog: false,
                filterForm: new CategoryModel(),
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
                this.$ajax.request(Api.category.list, {
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
                    确定删除以下分类字典吗？ <br>
                    [ ${nodes.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.category.delete, {
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

    .CategoryManagerView {
    }
</style>