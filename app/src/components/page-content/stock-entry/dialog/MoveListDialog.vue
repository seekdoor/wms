<template>
    <div class="MoveListDialog">
        <el-dialog
                width="600px"
                :visible="visible"
                @update:visible="showDialog"
                :title="title"
        >
            <div class="mb-xs">
                <el-button-curd-group
                        slot="buttons"
                        :delete-disabled="!selectedRow.length"
                        :edit-disabled="selectedRow.length !== 1"
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
                        :show-paginate="false"
                        :fix-height="400"
                        :sortable="null"
                >
                    <el-table-column
                            prop="id"
                            label="ID"
                            width="80"
                    ></el-table-column>

                    <template slot="operate" slot-scope="{row}">
                        <el-button-mini
                                type="text"
                                icon="delete"
                                @click.native="clickRowDeleteButton(row)"
                        ></el-button-mini>
                    </template>
                </table-panel>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import StockEntryModel from "@/project/model/StockEntryModel";
    import Api from "@/assets/api/Api";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "MoveListDialog",
        components: {ElButtonCurdGroup, ElButtonMini, TablePanel},
        props: {
            visible: {
                default: false
            },
            stockEntry: {
                default: new StockEntryModel()
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
                    确定删除以下出库信息吗？ <br>
                    [ ${nodes.map(x => x.id).join(',')} ]
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
        watch: {
            visible(v) {
                if (v) {
                    this.refreshData();
                }
            }
        },
        computed: {
            title() {
                return `单号 ${this.stockEntry.number}`
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MoveListDialog {
    }
</style>