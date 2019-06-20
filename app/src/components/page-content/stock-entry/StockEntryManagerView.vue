<template>
    <div class="StockEntryManagerView flex-box flex-column">
        <search-card-layout
                :loading="loading"
                @click-search="refreshData"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>{{ titleText }}单号</span>
                    <el-input v-model="filterForm.number"></el-input>
                </div>
                <div class="">
                    <span>订单号</span>
                    <el-input v-model="filterForm.orderNumber"></el-input>
                </div>
                <div class="">
                    <span>公司名称</span>
                    <el-input v-model="filterForm.companyName"></el-input>
                </div>
                <div class="">
                    <span>{{ titleText }}单分类</span>
                    <category-selector
                            v-model="filterForm.categoryId"
                            :type="categoryType"
                            :is-filter="true"
                    ></category-selector>
                </div>
                <div class="">
                    <span>创建时间</span>
                    <date-time-range-input
                            :start-time.sync="filterForm.startTime"
                            :end-time.sync="filterForm.endTime"
                    ></date-time-range-input>
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
                    operate-width="100"
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
                        prop="number"
                        :label="`${titleText}单号`"
                        sortable="custom"
                        width="180"
                ></el-table-column>

                <el-table-column
                        prop="orderNumber"
                        :label="`订单号`"
                        sortable="custom"
                        width="180"
                ></el-table-column>
                <el-table-column
                        prop="categoryId"
                        :label="`${titleText}类型`"
                        sortable="custom"
                        width="120"
                >
                    <template slot-scope="{row}">{{ row.categoryName }}</template>
                </el-table-column>
                <el-table-column
                        prop="companyId"
                        :label="`公司名称`"
                        sortable="custom"
                        width="200"
                >
                    <template slot-scope="{row}">{{ row.companyName }}</template>
                </el-table-column>

                <el-table-column
                        prop="moveCount"
                        :label="`明细条数`"
                        width="100"
                >
                    <template slot-scope="{row}">
                        <el-button-mini
                                type="text"
                                icon="iconfont icon-plus-circle"
                                @click.native="clickRowAddMoveButton(row)"
                        ></el-button-mini>
                        {{row.moveCount}}
                    </template>
                </el-table-column>

                <el-table-column
                        prop="remark"
                        :label="`备注`"
                        width="200"
                        :show-overflow-tooltip="true"
                >
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
                    <el-button-mini
                            type="text"
                            title="预览查看"
                            icon="iconfont icon-eye1"
                            @click.native="clickRowPreviewButton(row)"
                    ></el-button-mini>
                </template>
            </table-panel>


        </div>
        <stock-entry-add-dialog
                :visible.sync="showAddDialog"
                :edit-id="editId"
                @finish="refreshData"
        ></stock-entry-add-dialog>

        <move-list-dialog
                :visible.sync="showMoveListDialog"
                :stock-entry="selectStockEntry"
        ></move-list-dialog>

        <move-add-dialog
                :visible.sync="showMoveAddDialog"
                :edit-id="0"
                :stock-entry="selectStockEntry"
        ></move-add-dialog>
    </div>
</template>

<script>
    import DialogUtil from "@/util/DialogUtil";
    import StockEntryModel from "@/project/model/StockEntryModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import DateTimeRangeInput from "@/components/common/input/DateTimeRangeInput";
    import CategorySelector from "@/components/page-content/enum-selector/CategorySelector";
    import StockEntryAddDialog from "@/components/page-content/stock-entry/dialog/StockEntryAddDialog";
    import MoveListDialog from "@/components/page-content/stock-entry/dialog/MoveListDialog";
    import MoveAddDialog from "@/components/page-content/stock-entry/dialog/MoveAddDialog";

    export default {
        name: "StockEntryManagerView",
        components: {
            MoveAddDialog,
            MoveListDialog,
            StockEntryAddDialog,
            CategorySelector,
            DateTimeRangeInput,
            ElButtonCurdGroup,
            TablePanel,
            ElButtonMini,
            SearchCardLayout
        },
        props: {
            // 出入库 1:入库；2：出库
            type: {
                default: 1
            }
        },
        data() {
            return {
                loading: false,
                showAddDialog: false,
                showMoveListDialog: false,
                showMoveAddDialog: false,
                filterForm: new StockEntryModel(this.type),
                data: [],
                paginate: new PaginateModel(this.refreshData),
                selectedRow: [],
                sortingColumn: null,
                editId: 0,
                selectStockEntry: new StockEntryModel()
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
                this.$ajax.request(Api.stockEntry.list, {
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
            clickRowPreviewButton(row) {
                this.selectStockEntry = row;
                this.showMoveListDialog = true;
            },
            clickRowAddMoveButton(row){
                this.selectStockEntry = row;
                this.showMoveAddDialog = true;
            },
            delete(nodes) {
                DialogUtil.confirm(`
                    确定删除以下入库单吗？ <br>
                    [ ${nodes.map(x => x.number).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.stockEntry.delete, {
                        ids: nodes.map(x => x.id).join(',')
                    });
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },
        },
        watch: {},
        computed: {
            titleText() {
                return this.type === 1 ? '入库' : '出库';
            },
            categoryType() {
                return this.type === 1 ? 3 : 4;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockEntryManagerView {
    }
</style>