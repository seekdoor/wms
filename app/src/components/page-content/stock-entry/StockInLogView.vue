<template>
    <div class="StockInLogView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>入库单号</span>
                    <el-input v-model="filterForm.stockEntryNumber"></el-input>
                </div>
                <div class="">
                    <span>订单号</span>
                    <el-input v-model="filterForm.stockEntryOrderNumber"></el-input>
                </div>
                <div class="">
                    <span>物料编号</span>
                    <el-input v-model="filterForm.materialCode"></el-input>
                </div>
                <div class="">
                    <span>物料名称</span>
                    <el-input v-model="filterForm.materialName"></el-input>
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
                    :operate-show="false"
                    :show-selection="false"
                    :show-all-warehouse="true"
            >
                <el-table-column
                        prop="id"
                        label="ID"
                        sortable="custom"
                        width="80"
                ></el-table-column>

                <el-table-column
                        prop="stk_e.number"
                        label="入库单号"
                        sortable="custom"
                        v-if="type === 1"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.stockEntryNumber }}</template>
                </el-table-column>

                <el-table-column
                        prop="stk_ts.number"
                        label="出库单号"
                        sortable="custom"
                        v-if="type === 2"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.stockEntryNumber }}</template>
                </el-table-column>

                <el-table-column
                        prop="stk_e.order_number"
                        label="订单号"
                        sortable="custom"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.stockEntryOrderNumber }}</template>
                </el-table-column>

                <el-table-column
                        prop="materialId"
                        label="物料编号"
                        sortable="custom"
                        width="100"
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
                        prop="materialId"
                        label="物料名称"
                        sortable="custom"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.materialName }}</template>
                </el-table-column>

                <el-table-column
                        prop="planQuantity"
                        label="计划数量"
                        sortable="custom"
                        width="100"
                ></el-table-column>

                <el-table-column
                        prop="planQuantity"
                        label="实际数量"
                        sortable="custom"
                        width="100"
                ></el-table-column>

                <el-table-column
                        prop="materialUnitName"
                        label="单位"
                        width="80"
                ></el-table-column>

                <el-table-column
                        prop="approveUid"
                        label="审批人"
                        sortable="custom"
                        width="100"
                >
                    <template slot-scope="{row}">{{ row.approveUserName }}</template>
                </el-table-column>

                <el-table-column
                        prop="approveTime"
                        label="审批时间"
                        sortable="custom"
                        width="140"
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
    import MoveModel from "@/project/model/MoveModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";

    export default {
        name: "StockInLogView",
        components: {TablePanel, ElButtonMini, SearchCardLayout},
        props: {
            type: {
                default: 1
            }
        },
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new MoveModel(),
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
                let api = this.type === 1 ? Api.move.stockInList : Api.move.stockOutList;
                console.log(this.sortingColumn);
                this.$ajax.request(api, {
                    ...EcUtil.getSortField(this.sortingColumn, 't'),
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
        },
        watch: {},
        computed: {
            typeText() {
                return this.type === 1 ? '入库' : '出库'
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockInLogView {
    }
</style>