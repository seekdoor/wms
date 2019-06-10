<template>
    <div class="StockManagerView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>货架编号</span>
                    <el-input v-model="filterForm.code"></el-input>
                </div>
                <div class="">
                    <span>货架名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
                <div class="">
                    <span>所属位置</span>
                    <warehouse-selector
                            width="300px"
                            :level="2"
                            @change="warehouseSelectorChange"
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
                        label="货架编号"
                        sortable="custom"
                        width="120"
                ></el-table-column>

                <el-table-column
                        prop="name"
                        label="货架名称"
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
                        prop="reservoirId"
                        label="所属仓位"
                        sortable="custom"
                        width="160"
                >
                    <template slot-scope="{row}">{{ row.reservoirName }}</template>
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
                        :show-overflow-tooltip="true"
                        sortable="custom"
                        width="200"
                >
                    <template slot-scope="{row}">{{ row.reservoirName }}</template>
                </el-table-column>

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

    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import DialogUtil from "@/util/DialogUtil";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import StockModel from "@/project/model/StockModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";

    export default {
        name: "StockManagerView",
        components: {TablePanel, ElButtonMini, WarehouseSelector, ElButtonCurdGroup, SearchCardLayout},
        props: {},
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new StockModel(),
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
            },
            refreshData() {
                this.loading = true;
                this.$ajax.request(Api.warehouse.stockList, {
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
                    确定删除以下货架吗？ <br>
                    [ ${nodes.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.warehouse.stockDelete, {
                        ids: nodes.map(x => x.id).join(',')
                    });
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },
            warehouseSelectorChange(routeIds) {
                switch (routeIds.length) {
                    case 0 :
                        this.filterForm.warehouseId = 0;
                        this.filterForm.reservoirId = 0;
                        break;
                    case 1:
                        this.filterForm.warehouseId = routeIds[0];
                        this.filterForm.reservoirId = 0;
                        break;
                    case 2:
                        this.filterForm.warehouseId = routeIds[0];
                        this.filterForm.reservoirId = routeIds[1];
                        break;
                    default:
                        this.filterForm.warehouseId = 0;
                        this.filterForm.reservoirId = 0;
                        break;
                }
            }
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockManagerView {
    }
</style>