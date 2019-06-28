<template>
    <div class="InventorySearchView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>物料名称</span>
                    <el-input v-model="filterForm.materialName"></el-input>
                </div>
                <div class="">
                    <span>仓库货架</span>
                    <warehouse-selector
                            :is-filter="true"
                            :level="3"
                            @change="warehouseChange"
                    ></warehouse-selector>
                </div>
            </div>
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
                    :show-all-warehouse="true"
                    :operate-show="false"
                    :show-selection="false"
            >
                <el-table-column
                        prop="id"
                        label="ID"
                        sortable="custom"
                        width="80"
                ></el-table-column>

                <el-table-column
                        prop="materialId"
                        label="物料编号"
                        sortable="custom"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.materialCode }}</template>
                </el-table-column>

                <el-table-column
                        prop="materialId"
                        label="物料名称"
                        sortable="custom"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.materialName }}</template>
                </el-table-column>

                <el-table-column
                        prop="quantity"
                        label="库存数量"
                        sortable="custom"
                        width="120"
                ></el-table-column>

                <el-table-column
                        prop="mtlUnitName"
                        label="单位"
                        sortable="custom"
                        width="80"
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
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import EcUtil from "@/util/EcUtil";
    import InventoryModel from "@/project/model/InventoryModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import Api from "@/assets/api/Api";
    import WarehouseSelector from "@/components/page-content/enum-selector/WarehouseSelector";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";

    export default {
        name: "InventorySearchView",
        components: {TablePanel, ElButtonMini, WarehouseSelector, SearchCardLayout},
        props: {},
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new InventoryModel(),
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
                this.$ajax.request(Api.inventory.list, {
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

            warehouseChange(nodes) {
                let l = nodes.length;
                this.filterForm.warehouseId = l > 0 ? nodes[0] : 0;
                this.filterForm.reservoirId = l > 1 ? nodes[1] : 0;
                this.filterForm.stockId = l > 2 ? nodes[2] : 0;
            }

        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .InventorySearchView {
    }
</style>