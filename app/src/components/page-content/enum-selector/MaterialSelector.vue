<template>
    <div class="MaterialSelector">
        <el-cascader
                :style="{'width' : width}"
                :options="materialTree"
                :show-all-levels="true"
                :props="{'value': 'id', 'label' : 'name'}"
                :value="valueUse"
                @input="change"
        ></el-cascader>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";
    import CollectionUtil from "@/util/CollectionUtil";

    export default {
        name: "MaterialSelector",
        components: {},
        props: {
            types: {
                default: ''
            },
            value: {
                default: 0
            },
            width: {
                default: '200px'
            },
            filterIds: {
                default() {
                    return [];
                }
            },
            needFilter:{
                default : false
            }
        },
        data() {
            return {
                materials: []
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshList();
            },
            refreshList() {
                this.$ajax.request(Api.material.selectByType, {
                    types: this.types
                }).then(resp => {
                    this.materials = resp;
                });
            },
            change(a) {
                if (!a) return;
                this.$emit('input', a[a.length - 1]);
            }
        },
        watch: {},
        computed: {
            materialTree() {
                let tree = [];
                this.materials.forEach(m => {
                    let fn = tree.find(f => {
                        return m.categoryId === f.id
                    });
                    if (fn) {
                        fn.children.push(m);
                    } else {
                        let f = {
                            id: m.categoryId,
                            name: m.categoryName,
                            children: [m]
                        };
                        tree.push(f);
                    }
                });
                if( this.needFilter || this.filterIds.length){
                    tree = CollectionUtil.filterTree(tree, x => this.filterIds.includes(x.id), 'children', 2);
                }
                return tree;
            },
            valueUse() {
                let c = this.materials.find(x => x.id === this.value);
                if (!c) return [];
                return [c.categoryId, c.id];
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .MaterialSelector {
    }
</style>