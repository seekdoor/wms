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
                    ></el-table-column>
                    <el-table-column
                            type="selection"
                            width="43"
                            fixed="left"
                    ></el-table-column>
                    <el-table-column
                            label="操作"
                            :fixed="fixed"
                            :width="operateWidth"
                    >
                        <template slot-scope="scope">
                            <slot name="operate" :row="scope.row"></slot>
                        </template>
                    </el-table-column>
                    <slot></slot>
                    <el-table-column
                            prop="createUid"
                            label="创建人"
                            sortable="custom"
                            width="100"
                            v-if="createUserShow"
                    >
                        <template slot-scope="{row}">{{ row.createUserName }}</template>
                    </el-table-column>
                    <el-table-column
                            prop="createTime"
                            label="创建时间"
                            sortable="custom"
                            width="140"
                            v-if="createUserShow"
                    ></el-table-column>
                    <el-table-column
                            prop="updateUid"
                            label="修改人"
                            sortable="custom"
                            width="100"
                            v-if="updateUserShow"
                    >
                        <template slot-scope="{row}">{{ row.updateUserName }}</template>
                    </el-table-column>

                    <el-table-column
                            prop="updateTime"
                            label="修改时间"
                            sortable="custom"
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
                    <div class="">
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
                    this.height = this.$refs.container.offsetHeight - 70;
                });
            },
            handleSelectionChange(nodes) {
                this.updateSelectedRow(nodes);
                this.$emit('selection-change', nodes)
            },
            rowIndex(index) {
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
            }
        },

        watch: {
            resize() {
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