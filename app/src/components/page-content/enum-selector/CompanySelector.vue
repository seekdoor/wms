<template>
    <div class="CompanySelector">
        <el-select
                :value="valueUse"
                @input="change"
                filterable
                :placeholder="placeholder"
                :filter-method="filterMethod"
        >
            <el-option :label="filterAllText" :value="0" v-if="isFilter"></el-option>
            <el-option v-for="item in companiesFilter"
                       :key="item.id"
                       :label="item.name"
                       :value="item.id"
            ></el-option>
        </el-select>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";

    export default {
        name: "CompanySelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter: {
                default: false
            },
            category: {
                default: 0
            },
            placeholder: {
                default: '请选择'
            },
            filterAllText: {
                default: '全部'
            }
        },
        data() {
            return {
                companies: [],
                filterString: '',
                pinyinMatch: require('pinyin-match')
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshList();
                this.change(0);
            },
            change(v) {
                this.$emit('input', v);
            },
            refreshList() {
                this.$ajax.request(Api.company.selectByCategory, {
                    category: this.category
                }).then(resp => {
                    this.companies = resp;
                })
            },
            filterMethod(a) {
                this.filterString = a;
            }
        },
        watch: {
            type() {
                this.refreshList();
            }
        },
        computed: {
            valueUse() {
                if (this.value) {
                    return this.value;
                }
                return this.isFilter ? 0 : null;
            },
            companiesFilter() {
                if (this.filterString === '') {
                    return this.companies;
                }
                return this.companies.filter(x => {
                    return this.pinyinMatch.match(x.name, this.filterString)
                })
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .CompanySelector {
    }
</style>