<template>
    <div class="RoleManagerView flex-box flex-column">
        <search-card-layout
                :default-show="false"
                title="角色查询与操作"
        >
            <div class="flex-box flex-center search-card">
                <div class="">
                    <span>角色名称</span>
                    <el-input v-model="filterForm.name"></el-input>
                </div>
            </div>
            <div class="" slot="buttons">
                <el-button-curd-group
                        :delete-disabled="!selectedRow.length"
                        :edit-disabled="selectedRow.length !== 1"
                        @click-add="clickAddButton"
                        @click-edit="clickEditButton"
                        @click-delete="clickDeleteButton"
                ></el-button-curd-group>
            </div>
        </search-card-layout>

        <div class="flex-grow mb-xs">
            <div class="flex-box h100p ">
                <div class="flex-grow overflow-h ">
                    <table-panel
                            :loading="loading"
                            :data="data"
                            :paginate="paginate"
                            operate-width="80"
                            :selectedRow.sync="selectedRow"
                            @sort-change="sortChange"
                    >
                        <el-table-column
                                prop="id"
                                label="ID"
                                sortable="custom"
                                width="80"
                        ></el-table-column>
                        <el-table-column
                                prop="name"
                                label="角色名称"
                                sortable="custom"
                                min-width="140"
                        ></el-table-column>
                        <el-table-column
                                prop="createUid"
                                label="创建人"
                                sortable="custom"
                                width="100"
                        >
                            <template slot-scope="{row}">{{row.createUserName}}</template>
                        </el-table-column>
                        <el-table-column
                                prop="createTime"
                                label="创建时间"
                                sortable="custom"
                                min-width="140"
                        ></el-table-column>
                        <el-table-column
                                prop="updateUid"
                                label="修改人"
                                sortable="custom"
                                width="100"
                        >
                            <template slot-scope="{row}">{{row.updateUserName}}</template>
                        </el-table-column>
                        <el-table-column
                                prop="updateTime"
                                label="修改时间"
                                sortable="custom"
                                min-width="140"
                        ></el-table-column>

                        <template slot="operate" slot-scope="{row}">
                            <el-button-mini type="text" icon="edit"></el-button-mini>
                            <el-button-mini type="text" icon="delete"></el-button-mini>
                        </template>
                    </table-panel>
                </div>
                <div class="flex-shrink-off ml-xs  " style="width: 300px;max-height: 100%;">
                    <div class=" el-card box-card is-always-shadow pa-xs bg-red" style="max-height: 100%;">
                        <auth-manager-content ></auth-manager-content>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
    import SearchCardLayout from "@/components/layout-component/SearchCardLayout";
    import RoleModel from "@/project/model/RoleModel";
    import ElButtonCurdGroup from "@/components/common/button/ElButtonCurdGroup";
    import TablePanel from "@/components/layout-component/TablePanel";
    import PaginateModel from "@/project/model/PaginateModel";
    import Api from "@/assets/api/Api";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import AuthManagerContent from "@/components/page-content/user/content/AuthManagerContent";

    export default {
        name: "RoleManagerView",
        components: {AuthManagerContent, ElButtonMini, TablePanel, ElButtonCurdGroup, SearchCardLayout},
        props: {},
        data() {
            return {
                loading: true,
                showAddDialog: false,
                filterForm: new RoleModel(),
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
                this.$ajax.request(Api.role_auth.roleList, {
                    ...this.paginate.getJSON(),
                    ...this.filterForm,
                }).then(resp => {
                    this.data = resp.list;
                    this.paginate.setPaginate(resp);
                }).finally(() => this.loading = false)
            },
            clickAddButton() {

            },
            clickEditButton() {

            },
            clickDeleteButton() {

            },
            sortChange(column) {
                this.sortingColumn = column;
                this.$nextTick(() => this.refreshData());
            },
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .RoleManagerView {
    }
</style>