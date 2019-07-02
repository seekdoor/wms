<template>
    <div class="TablePanel h100p"
         ref="container"
    >
        <div class="el-card box-card is-always-shadow">
            <div class="flex-box flex-column ma-xs ">
                <el-table
                        :data="data"
                        :max-height="height"
                        v-loading="loading"
                        border
                        :height="fixHeight"
                        style="width: 100%;"
                        @selection-change="handleSelectionChange"
                        ref="table"
                        @row-dblclick="dblClickRow"
                        @sort-change="sortChange"
                >
                    <el-table-column
                            type="index"
                            fixed="left"
                            :index="rowIndex"
                            width="60"
                            v-if="showIndex"
                    ></el-table-column>
                    <el-table-column
                            type="selection"
                            width="43"
                            fixed="left"
                            v-if="showSelection"
                    ></el-table-column>
                    <el-table-column
                            label="操作"
                            :fixed="fixed"
                            :width="operateWidth"
                            v-if="operateShow"
                    >
                        <template slot-scope="scope">
                            <slot name="operate" :row="scope.row"></slot>
                        </template>
                    </el-table-column>
                    <slot></slot>
                    <el-table-column
                            prop="warehouseId"
                            label="仓库"
                            :sortable="sortable"
                            width="140"
                            :show-overflow-tooltip="true"
                            v-if="showAllWarehouse || showWarehouse"
                    >
                        <template slot-scope="{row}">({{ row.warehouseCode }}){{ row.warehouseName }}</template>
                    </el-table-column>
                    <el-table-column
                            prop="reservoirId"
                            label="库区"
                            :sortable="sortable"
                            width="140"
                            :show-overflow-tooltip="true"
                            v-if="showAllWarehouse || showReservoir"
                    >
                        <template slot-scope="{row}">({{ row.reservoirCode }}){{ row.reservoirName }}</template>
                    </el-table-column>
                    <el-table-column
                            prop="stockId"
                            label="货架"
                            :sortable="sortable"
                            width="140"
                            :show-overflow-tooltip="true"
                            v-if="showAllWarehouse || showStock"
                    >
                        <template slot-scope="{row}">({{ row.stockCode }}){{ row.stockName }}</template>
                    </el-table-column>
                    <el-table-column
                            prop="createUid"
                            label="创建人"
                            :sortable="sortable"
                            width="100"
                            v-if="createUserShow"
                    >
                        <template slot-scope="{row}">{{ row.createUserName }}</template>
                    </el-table-column>
                    <el-table-column
                            prop="createTime"
                            label="创建时间"
                            :sortable="sortable"
                            width="140"
                            v-if="createUserShow"
                    ></el-table-column>
                    <el-table-column
                            prop="updateUid"
                            label="修改人"
                            :sortable="sortable"
                            width="100"
                            v-if="updateUserShow"
                    >
                        <template slot-scope="{row}">{{ row.updateUserName }}</template>
                    </el-table-column>

                    <el-table-column
                            prop="updateTime"
                            label="修改时间"
                            :sortable="sortable"
                            width="140"
                            v-if="updateUserShow"
                    ></el-table-column>
                </el-table>
                <div class="flex-box mt-xs flex-center">
                    <div class="flex-grow">

                    </div>
                    <div class="pr-sm">
                        <el-popover
                                placement="top"
                                :width="columnListWidth"
                                v-model="showColumnList"
                                v-if="false"
                        >
                            <el-checkbox-group v-model="checkFieldList">
                                <el-checkbox
                                        :label="item" v-for="item in columnListCheckbox"
                                        :key="item"
                                        class="disp-b"
                                ></el-checkbox>
                            </el-checkbox-group>
                            <el-button
                                    class="only-icon"
                                    slot="reference"
                                    icon="iconfont icon-th-list"
                            >
                            </el-button>
                        </el-popover>
                        <slot name="buttons"></slot>
                    </div>
                    <div class="" v-if="showPaginate">
                        <el-pagination-use
                                :paginate="paginate"
                        ></el-pagination-use>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import ElPaginationUse from "@/components/common/ElPaginationUse";
    import ElementUtil from "@/util/ElementUtil";
    import PaginateModel from "@/project/model/PaginateModel";
    import CollectionUtil from "@/util/CollectionUtil";
    import ElButtonMini from "@/components/common/button/ElButtonMini";

    export default {
        name: "TablePanel",
        components: {ElButtonMini, ElPaginationUse},
        props: {
            data: {
                default() {
                    return []
                }
            },
            loading: {
                default: false
            },
            paginate: {
                default() {
                    return new PaginateModel()
                }
            },
            operateShow: {
                default: true
            },
            fixed: {
                default: 'left'
            },
            operateWidth: {
                default: '100'
            },
            selectedRow: {
                default() {
                    return [];
                }
            },
            columnListWidth: {
                default: '120'
            },
            createUserShow: {
                default: false,
            },
            updateUserShow: {
                default: false
            },
            showAllWarehouse: {
                default: false,
            },
            showWarehouse: {
                default: false,
            },
            showReservoir: {
                default: false,
            },
            showStock: {
                default: false
            },
            showPaginate: {
                default: true
            },
            fixHeight: {
                default: null
            },
            sortable: {
                default: 'custom'
            },
            showIndex: {
                default: true,
            },
            showSelection: {
                default: true
            }
        },
        data() {
            return {
                height: 300,
                showColumnList: false,
                checkFieldList: [],
                columns: [],
            }
        },
        mounted() {
            this.reload();
            this.columns = this.$refs.table.store.states.columns.concat([]);
            this.$nextTick(() => {
                this.resizeHeight();
            });
        },
        methods: {
            reload() {
            },

            resizeHeight() {
                if (ElementUtil.isHidden(this.$refs.container)) return;
                this.height = 0;
                this.$nextTick(() => {
                    this.height = this.fixHeight ? this.fixHeight : this.$refs.container.offsetHeight - 70;
                });
            },
            handleSelectionChange(nodes) {
                this.updateSelectedRow(nodes);
                this.$emit('selection-change', nodes)
            },
            rowIndex(index) {
                if (!this.showPaginate) {
                    return index + 1;
                }
                return this.paginate.startRow + index;
            },
            selectRows() {
                this.data.forEach(row => {
                    this.$refs.table.toggleRowSelection(row,
                        !!this.selectedRow.find(r => r === row)
                    );
                });
            },
            dblClickRow(row) {
                this.updateSelectedRow(CollectionUtil.mergeDiff(this.selectedRow, [row]))
            },
            updateSelectedRow(nodes) {
                this.$emit('update:selectedRow', nodes);
            },
            sortChange(column, prop, order) {
                this.$emit('sort-change', column, prop, order);
            },
            hiddenField() {
                document.querySelectorAll("." + this.columns[0].id).forEach(t => {
                    t.style.visiblity = 'hidden';
                });
            },
            doLayOut() {
                this.$refs.table.doLayout();
            }
        },

        watch: {
            data() {
                this.resizeHeight();
            },
            selectedRow() {
                this.selectRows();
            },
            checkFieldList(v) {
                this.hiddenField();
            }
        },
        computed: {
            resize() {
                return this.$store.state.searchShowHidden;
            },
            columnListCheckbox() {
                return this.columns.filter(c => {
                    return c.type === 'default' && !c.fixed
                }).map(c => c.label)
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .TablePanel {
    }
</style>