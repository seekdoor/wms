<template>
    <div class="CompanyManagerView flex-box flex-column">
        <search-card-layout
                @click-search="refreshData"
                :loading="loading"
        >
            <el-form class="flex-box flex-center search-card">
                <div class="" style="width: 300px;">
                    <span class="">公司编码</span>
                    <el-input v-model="filterForm.code"></el-input>
                </div>
                <div class="" style="width: 300px;">
                    <span class="">公司名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
                <div class="" style="width: 150px;">
                    <span class="">分类</span>
                    <company-category-selector
                            v-model="filterForm.category"
                            :is-filter="true"
                    ></company-category-selector>
                </div>
                <div class="" style="width: 200px;">
                    <span class="">联系人姓名</span>
                    <el-input v-model="filterForm.contactName"></el-input>
                </div>
                <div class="" style="width: 170px;">
                    <span class="">手机号</span>
                    <el-input v-model="filterForm.mobile"></el-input>
                </div>
                <div class="" style="width: 180px;">
                    <span class="">座机</span>
                    <el-input v-model="filterForm.phone"></el-input>
                </div>
                <div class="" style="width: 260px;">
                    <span class="">Email</span>
                    <el-input v-model="filterForm.email"></el-input>
                </div>
            </el-form>

            <el-button-curd-group
                    slot="buttons"
                    :edit-disabled="selectedRow.length !== 1"
                    :delete-disabled="!selectedRow.length"
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
                    operate-width="65"
                    :selected-row.sync="selectedRow"
                    @sort-change="sortChange"
                    :update-user-show="true"
                    :create-user-show="true"
            >

                <el-table-column
                        prop="id"
                        label="ID"
                        width="80"
                        sortable="custom"
                ></el-table-column>

                <el-table-column
                        prop="code"
                        label="编码"
                        width="180"
                        sortable="custom"
                ></el-table-column>

                <el-table-column
                        prop="category"
                        label="分类"
                        width="80"
                        sortable="custom"
                >
                    <template slot-scope="{row}">
                        <el-tag type="info" v-if="row.category === 1">供应商</el-tag>
                        <el-tag type="primary" v-if="row.category === 2">客户</el-tag>
                        <el-tag type="warning" v-if="row.category === 3">承运商</el-tag>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="name"
                        label="公司名称"
                        min-width="250"
                        sortable="custom"
                ></el-table-column>

                <el-table-column
                        prop="contactName"
                        label="联系人"
                        width="120"
                        sortable="custom"
                ></el-table-column>

                <el-table-column
                        prop="mobile"
                        label="联系人手机"
                        width="120"
                        sortable="custom"
                ></el-table-column>

                <el-table-column
                        prop="phone"
                        label="座机"
                        width="120"
                        sortable="custom"
                ></el-table-column>

                <template slot="operate" slot-scope="{row}">
                    <el-button-mini icon="edit" type="text" @click.native="clickRowEdit(row)"></el-button-mini>
                    <el-button-mini icon="delete" type="text" @click.native="clickRowDelete(row)"></el-button-mini>
                </template>


            </table-panel>
        </div>
        <company-add-dialog
                :visible.sync="showCompanyDialog"
                :edit-id="editId"
                @finish="refreshData"
        ></company-add-dialog>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import CompanyModel from "@/project/model/CompanyModel";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import PaginateModel from "@/project/model/PaginateModel";
    import TablePanel from "@/components/layout-component/TablePanel";
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import CompanyAddDialog from "@/components/page-content/company/dialog/CompanyAddDialog";
    import DialogUtil from "@/util/DialogUtil";
    import CompanyCategorySelector from "@/components/page-content/company/comp/CompanyCategorySelector";
    import CompanySelector from "@/components/page-content/enum-selector/CompanySelector";

    export default {
        name: "CompanyManagerView",
        components: {
            CompanySelector,
            CompanyCategorySelector,
            CompanyAddDialog, ElButtonMini, TablePanel, ElButtonCurdGroup, SearchCardLayout
        },
        props: {},
        data() {
            return {
                showCompanyDialog: false,
                selectedRow: [],
                loading: false,
                data: [],
                filterForm: new CompanyModel(),
                paginate: new PaginateModel(this.refreshData),
                sortingColumn: [],
                editId: 0,
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshData();
                this.filterForm.category = 0;
            },
            refreshData() {
                this.loading = true;
                this.$ajax.request(Api.company.list, {
                    ...this.filterForm,
                    ...this.paginate.getJSON(),
                    ...EcUtil.getSortField(this.sortingColumn)
                }).then(resp => {
                    this.data = resp.list;
                    this.paginate.setPaginate(resp);
                }).finally(() => this.loading = false)
            },
            sortChange(column) {
                this.sortingColumn = column;
                this.$nextTick(() => this.refreshData());
            },
            clickAddButton() {
                this.editId = 0;
                this.showCompanyDialog = true;
            },
            clickEditButton() {
                this.editId = this.selectedRow[0].id;
                this.showCompanyDialog = true;
            },
            clickDeleteButton() {
                this.deleteRows(this.selectedRow);
            },
            clickRowDelete(row) {
                this.deleteRows([row])
            },
            clickRowEdit(row) {
                this.editId = row.id;
                this.showCompanyDialog = true;
            },
            deleteRows(rows) {
                DialogUtil.confirm(`
                    确定要删除以下合作伙伴吗? <br>
                    [ ${rows.map(x => x.name).join(',')} ]
                `).then(() => {
                    return this.$ajax.request(Api.company.delete, {
                        ids: rows.map(x => x.id).join(',')
                    })
                }).then(resp => {
                    DialogUtil.toastSuccess(resp);
                    this.refreshData();
                });
            },
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CompanyManagerView {
    }
</style>