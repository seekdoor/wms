<template>
    <div class="WarehouseSelector">
        <el-cascader
                :style="{'width' : width}"
                :value="selectArray"
                :placeholder=" isFilter? '全部' : plcText"
                :options="tree"
                :props="{value:'id', label: 'name'}"
                @change="itemChange"
                :change-on-select="isFilter"
        ></el-cascader>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";
    import CollectionUtil from "@/util/CollectionUtil";

    export default {
        name: "WarehouseSelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: false
            },
            level: {
                default: 1
            },
            visible: {
                default: true
            },
            width: {
                default: '200px'
            },
            filterIds: {
                default() {
                    return [];
                }
            },
            needFilter: {
                default :false
            }
        },
        data() {
            return {
                warehouses: [],
                reservoirs: [],
                stocks: [],
                tree: [],
                selectArray: []
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshList();
                this.change(this.isFilter ? 0 : null);
            },
            change(v) {
                this.$emit('input', v);
            },
            refreshList() {
                this.$ajax.request(Api.warehouse.warSelectAll).then(resp => {
                    this.warehouses = resp;
                    if (this.level > 1) {
                        return this.$ajax.request(Api.warehouse.resSelectAll);
                    }
                }).then(resp => {
                    this.reservoirs = resp;
                    if (this.level > 2) {
                        return this.$ajax.request(Api.warehouse.stockSelectAll);
                    }
                }).then(resp => {
                    this.stocks = resp;
                }).finally(() => this.createTree())
            },

            createTree() {
                this.tree = CollectionUtil.listToTreeByTwoList(
                    this.warehouses,
                    CollectionUtil.listToTreeByTwoList(this.reservoirs, this.stocks, 'reservoirId'),
                    'warehouseId'
                );
                if (this.isFilter) {
                    this.tree = [{
                        id: 0,
                        name: '全部'
                    }].concat(this.tree);
                } else if ( this.needFilter || this.filterIds.length) {
                    this.tree = CollectionUtil.filterTree(this.tree, x => this.filterIds.includes(x.id), 'children', 3);
                }
                this.refreshSelect();
            },

            refreshSelect() {
                this.selectArray = CollectionUtil.getTreeRoute(this.tree, x => x.id === this.value, 'id', 'children', this.level);
            },

            itemChange(nodes) {
                if ( !this.isFilter && nodes.length < this.level) return;
                this.$emit('input', nodes.length !== this.level ? 0 : nodes[nodes.length - 1]);
                this.$emit('change', nodes);
            }

        },
        watch: {
            visible(v) {
                if (v) {
                    this.refreshList();
                }
            },
            value() {
                this.refreshSelect();
            },
            filterIds() {
                this.createTree();
            }
        },
        computed: {
            valueUse() {
                if (this.value) {
                    return this.value;
                }
                return this.isFilter ? 0 : null;
            },
            plcText() {
                return '请选择' + ['仓库', '仓位', '货架'][this.level - 1]
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .WarehouseSelector {
    }
</style>