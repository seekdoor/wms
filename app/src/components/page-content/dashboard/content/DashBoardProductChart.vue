<template>
    <div class="DashBoardProductChart">
        <el-card shadow="always">
            <div class="mb-xs">产品当前库存</div>
            <div>
                <div class="" ref="chartBlock" style="height: 400px;"></div>
            </div>
        </el-card>
    </div>
</template>

<script>
    import GbObject from "@/project/var/GbObject";

    export default {
        name: "DashBoardProductChart",
        components: {},
        props: {},
        data() {
            return {
                option: {
                    title: {
                        text: '产品库存',
                        subtext: '纯属虚构',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    // legend: {
                    //     x: 'center',
                    //     y: 'bottom',
                    //     data: ['吸尘器T700', '吸尘器T100', '吹风机S20', '吹风机S30', '扫地机器人ST30']
                    // },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    series: [
                        {
                            name: '当前库存',
                            type: 'pie',
                            radius: [20, 110],
                            center: ['50%', '50%'],
                            roseType: 'radius',
                            data: [
                                {value: 10, name: '吸尘器T700'},
                                {value: 5, name: '吸尘器T100'},
                                {value: 15, name: '吹风机S20'},
                                {value: 25, name: '吹风机S30'},
                                {value: 20, name: '扫地机器人ST30'},
                            ]
                        },
                    ]
                }
            }
        },
        mounted() {
            this.reload();
        }
        ,
        methods: {
            reload() {
                // 基于准备好的dom，初始化echarts实例
                let myChart = GbObject.echarts.init(this.$refs.chartBlock);
                // 绘制图表
                myChart.setOption(this.option);
            }
            ,
        }
        ,
        watch: {}
        ,
        computed: {}
        ,

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DashBoardProductChart {
    }
</style>