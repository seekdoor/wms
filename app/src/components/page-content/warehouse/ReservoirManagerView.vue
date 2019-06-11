<template>
    <div class="ReservoirManagerView flex-box flex-column">

        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>库位编号</span>
                    <el-input v-model="filterForm.code"></el-input>
                </div>
                <div class="">
                    <span>库位名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
                <div class="">
                    <span>所属仓库</span>
                    <warehouse-selector
                            :level="1"
                            v-model="filterForm.warehouseId"
                            :is-filter="true"
                    ></warehouse-selector>
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
                        label="库区编码"
                        sortable="custom"
                        width="120"
                ></el-table-column>

                <el-table-column
                        prop="name"
                        label="库区名称"
                        sortable="custom"
                        width="160"
                ></el-table-column>

                <el-table-column
                        prop="warehouseId"
                        label="所属仓库"
                        sortable="custom"
                        width="160"
                >
                    <template slot-scope="{row}">{{ row.warehouseName }}</template>
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
                        sortable="custom"
                        :show-overflow-tooltip="true"
                        width="200"
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
        <reservoir-add-dialog
                :edit-id="editId"
                @finish="refreshData"
                :visible.sync="showAddDialog"
        ></reservoir-add-dialog>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import DialogUtil from "@/util/DialogUtil";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import ReservoirModel from "@/project/model/ReservoirModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import ReservoirAddDialog from "@/components/page-content/warehouse/dialog/ReservoirAddDialog";

    export default {
        name: "ReservoirManagerView",
        components: {
            ReservoirAddDialog,
            ElButtonCurdGroup, TablePanel, ElButtonMini, WarehouseSelector, SearchCardLayout
        },
        props: {},
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new ReservoirModel(),
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
                this.$ajax.request(Api.warehouse.resList, {
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
                    确定删除以下库区吗？ <br>
                    [ ${nodes.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.warehouse.resDelete, {
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

    .ReservoirManagerView {
    }
</style>