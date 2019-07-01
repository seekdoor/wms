<template>
    <div class="LoginRecordView flex-box flex-column">

        <search-card-layout
                :loading="loading"
                @click-search="refreshData"
        >
            <div class="flex-box search-card">
                <div class="">
                    <span>ip</span>
                    <el-input v-model="filterForm.ip"></el-input>
                </div>
                <div class="">
                    <span>类型</span>
                    <el-select v-model="filterForm.type" placeholder="选择审批状态">
                        <el-option label="所有状态" :value="0"></el-option>
                        <el-option v-for="item in typeEnum.list()"
                                   :key="item.id"
                                   :label="item.label"
                                   :value="item.id"
                        ></el-option>
                    </el-select>
                </div>
                <div class="">
                    <span>时间</span>
                    <date-time-range-input
                            :start-time.sync="filterForm.startTime"
                            :end-time.sync="filterForm.endTime"

                    ></date-time-range-input>
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
                    :show-selection="false"
                    :operate-show="false"
            >
                <el-table-column
                        prop="id"
                        label="ID"
                        sortable="custom"
                        width="80"
                ></el-table-column>

                <el-table-column
                        prop="ip"
                        label="IP地址"
                        sortable="custom"
                        min-width="90"
                ></el-table-column>

                <el-table-column
                        prop="uid"
                        label="用户"
                        sortable="custom"
                        width="80"
                >
                    <template slot-scope="{row}">{{row.realName}}</template>
                </el-table-column>

                <el-table-column
                        prop="type"
                        label="状态"
                        sortable="custom"
                        width="100"
                >
                    <template slot-scope="{row}">
                        <el-button-mini
                                type="text"
                        ><span :class=" row.type === 1 ? 'color-blue' : 'color-red'">{{row.typeText}}</span>
                        </el-button-mini>
                    </template>
                </el-table-column>

                <el-table-column
                        prop="createTime"
                        label="时间"
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
    import Api from "@/assets/api/Api";
    import EcUtil from "@/util/EcUtil";
    import PaginateModel from "@/project/model/PaginateModel";
    import LoginRecordModel from "@/project/model/LoginRecordModel";
    import TablePanel from "@/components/layout-component/TablePanel";
    import ElButtonMini from "@/components/common/button/ElButtonMini";
    import LoginRecordTypeEnum from "@/project/enum/LoginRecordTypeEnum";
    import DateTimeRangeInput from "@/components/common/input/DateTimeRangeInput";

    export default {
        name: "LoginRecordView",
        components: {DateTimeRangeInput, ElButtonMini, TablePanel, SearchCardLayout},
        props: {},
        data() {
            return {
                loading: false,
                showAddDialog: false,
                filterForm: new LoginRecordModel(),
                data: [],
                paginate: new PaginateModel(this.refreshData),
                selectedRow: [],
                sortingColumn: null,
                editId: 0,
                typeEnum: LoginRecordTypeEnum
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
                this.$ajax.request(Api.logger.loginRecordList, {
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
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .LoginRecordView {
    }
</style>