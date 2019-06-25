<template>
    <div class="MoveListDialog">
        <el-dialog
                width="1000px"
                :visible="visible"
                @update:visible="showDialog"
                :title="title"
                @closed="$emit('close')"
        >
            <div class="mb-xs fz-xs title-text-block">
                <span>{{ typeText }}单号：{{ stockEntry.number }}</span>
                <span>订单号：{{ stockEntry.number }}</span>
                <span>{{ typeText }}类型：{{ stockEntry.categoryName }}</span>
                <span>公司名称：{{ stockEntry.companyName }}</span>
            </div>
            <div class="mb-xs">
                <el-button-curd-group
                        slot="buttons"
                        :add-disabled="!canEdit"
                        :delete-disabled="!selectedRow.length || !canEdit"
                        :edit-disabled="selectedRow.length !== 1 || !canEdit"
                        @click-add="clickAddButton"
                        @click-edit="clickEditButton"
                        @click-delete="clickDeleteButton"
                ></el-button-curd-group>
            </div>
            <div class="">
                <table-panel
                        :loading="loading"
                        :data="data"
                        operate-width="80"
                        :selectedRow.sync="selectedRow"
                        :create-user-show="true"
                        :update-user-show="true"
                        :show-all-warehouse="true"
                        :show-paginate="false"
                        :fix-height="360"
                        :sortable="null"
                        :show-index="false"
                        ref="table"
                >
                    <el-table-column
                            prop="materialName"
                            label="物品名称"
                            width="160"
                    ></el-table-column>

                    <el-table-column
                            prop="planQuantity"
                            label="计划数量"
                            width="100"
                    ></el-table-column>

                    <el-table-column
                            prop="quantity"
                            label="实际数量"
                            width="100"
                    ></el-table-column>

                    <el-table-column
                            prop="materialUnitName"
                            label="单位"
                            width="70"
                    ></el-table-column>

                    <template slot="operate" slot-scope="{row}">
                        <el-button-mini
                                type="text"
                                icon="edit"
                                @click.native="clickRowEditButton(row)"
                                v-if="canEdit"
                        ></el-button-mini>
                        <el-button-mini
                                type="text"
                                icon="delete"
                                @click.native="clickRowDeleteButton(row)"
                                v-if="canEdit"
                        ></el-button-mini>
                    </template>
                </table-panel>
            </div>

        </el-dialog>
        <move-add-dialog
                :visible.sync="showAddDialog"
                :edit-id="editId"
                :type="type"
                :stock-entry="stockEntry"
                @finish="refreshData"
        ></move-add-dialog>
    </div>
</template>

<script>
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import StockEntryModel from "@/project/model/StockEntryModel";
    import Api from "@/assets/api/Api";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import DialogUtil from "@/util/DialogUtil";
    import MoveAddDialog from "@/components/page-content/stock-entry/dialog/MoveAddDialog";

    export default {
        name: "MoveListDialog",
        components: {MoveAddDialog, ElButtonCurdGroup, ElButtonMini, TablePanel},
        props: {
            visible: {
                default: false
            },
            stockEntry: {
                default() {
                    return new StockEntryModel();
                }
            },
            type: {
                default: 1
            }
        },
        data() {
            return {
                loading: false,
                showAddDialog: false,
                data: [],
                selectedRow: [],
                editId: 0,
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            showDialog(v) {
                this.$emit("update:visible", v);
            },
            refreshData() {
                this.loading = true;
                this.$ajax.request(Api.move.selectByStockEntryId, {
                    stockEntryId: this.stockEntry.id
                }).then(resp => {
                    this.data = resp;
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
                    确定删除以下${this.typeText}信息吗？ <br>
                    [ ${nodes.map(x => x.materialName).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.move.delete, {
                        ids: nodes.map(x => x.id).join(',')
                    });
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                })
            },
        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                }
            }
        },
        computed: {
            title() {
                return `${this.typeText}单明细 ${this.stockEntry.number}`
            },
            typeText() {
                return this.type === 1 ? '入库' : '出库';
            },
            canEdit() {
                return [1, 4].includes(this.stockEntry.status);
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MoveListDialog {
    }
</style>