<template>
    <div class="MaterialManagerContent flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>{{ nameText }}编号</span>
                    <el-input v-model="filterForm.code"></el-input>
                </div>
                <div class="">
                    <span>{{ nameText }}名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
                <div class="" style="width: 200px;">
                    <span>{{ nameText }}分类</span>
                    <category-selector
                            :type="categoryType"
                            v-model="filterForm.categoryId"
                            :is-filter="true"
                    ></category-selector>
                </div>
                <div class="" style="width: 130px;">
                    <span>单位</span>
                    <category-selector
                            :type="1"
                            v-model="filterForm.unitId"
                            :is-filter="true"
                    ></category-selector>
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
                        :label="`${nameText}编号`"
                        sortable="custom"
                        width="140"
                ></el-table-column>

                <el-table-column
                        prop="name"
                        :label="`${nameText}名称`"
                        sortable="custom"
                        width="160"
                ></el-table-column>

                <el-table-column
                        prop="categoryId"
                        :label="`${nameText}分类`"
                        sortable="custom"
                        width="140"
                >
                    <template slot-scope="{row}">{{ row.categoryName }}</template>
                </el-table-column>

                <el-table-column
                        prop="unitId"
                        label="单位"
                        sortable="custom"
                        width="80"
                >
                    <template slot-scope="{row}">{{ row.unitName }}</template>
                </el-table-column>

                <el-table-column
                        prop="safeNum"
                        label="安全数量"
                        sortable="custom"
                        width="100"
                ></el-table-column>

                <el-table-column
                        prop="validDay"
                        label="有效天数"
                        sortable="custom"
                        width="100"
                >
                    <template slot-scope="{row}">{{ row.validDay || "永久有效"}}</template>
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
                        width="160"
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
        <material-add-dialog
                :edit-id="editId"
                :visible.sync="showAddDialog"
                @finish="refreshData"
                :category-type="categoryType"
                :type="type"
        ></material-add-dialog>
    </div>
</template>

<script>
    import DialogUtil from "@/util/DialogUtil";
    import EcUtil from "@/util/EcUtil";
    import Api from "@/assets/api/Api";
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import MaterialModel from "@/project/model/MaterialModel";
    import PaginateModel from "@/project/model/PaginateModel";
    import MaterialAddDialog from "@/components/page-content/material/dialog/MaterialAddDialog";
    import CategorySelector from "@/components/page-content/enum-selector/CategorySelector";

    export default {
        name: "MaterialManagerContent",
        components: {
            CategorySelector,
            MaterialAddDialog,
            ElButtonMini,
            TablePanel,
            ElButtonCurdGroup,
            SearchCardLayout
        },
        props: {
            type: {
                default: 1
            },
        },
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new MaterialModel(),
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
                this.filterForm.type = this.type ;
                this.$ajax.request(Api.material.list, {
                    ...EcUtil.getSortField(this.sortingColumn),
                    ...this.paginate.getJSON(),
                    ...this.filterForm,
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
                    return this.$ajax.request(Api.material.delete, {
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
            categoryType() {
                return this.type === 1 ? 2 : 7;
            },
            nameText() {
                return this.type === 1 ? '物料' : '产品'
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MaterialManagerContent {
    }
</style>