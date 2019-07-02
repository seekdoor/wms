<template>
    <div class="DashBoardStockOutChart">
        <el-card shadow="always">
            <div class="mb-xs">出库记录</div>
            <div>
                <div class="" ref="chartBlock" style="height: 400px;"></div>
            </div>
        </el-card>
    </div>
</template>

<script>
    import GbObject from "@/project/var/GbObject";

    export default {
        name: "DashBoardStockOutChart",
        components: {},
        props: {},
        data() {
            return {
                option : {
                    title: {
                        text: '出库历史',
                        x: 'center'
                    },
                    tooltip: {},
                    xAxis: {
                        data: ['1月', '2月', '3月', '4月', '5月', '6月']
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data: [5, 20, 36, 10, 10, 20]
                    }]
                }
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                let myCharts = GbObject.echarts.init(this.$refs.chartBlock);
                myCharts.setOption(this.option);
            },
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DashBoardStockOutChart {
    }
</style>