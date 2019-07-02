<template>
    <div class="DashBoardTopCards">
        <el-row :gutter="10">
            <el-col :span="6">
                <el-card shadow="hover" class="cursor-p dash-card" style="padding: 10px">
                    <div class="">本月入库</div>
                    <div class="fz-lg text-right ">
                        <span>{{ $_ecUtil.numberDivide(123456.00) }}</span>
                        <span class="fz-sm" style="margin-left: 2px;">件</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" class="cursor-p dash-card" style="padding: 10px">
                    <div class="">本月出库库</div>
                    <div class="fz-lg text-right ">
                        <span>{{ $_ecUtil.numberDivide(123456.00) }}</span>
                        <span class="fz-sm" style="margin-left: 2px;">件</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" class="cursor-p dash-card" style="padding: 10px">
                    <div class="">物料库存紧张</div>
                    <div class="fz-lg text-right ">
                        <span>3</span>
                        <span class="fz-sm" style="margin-left: 2px;">个</span>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card shadow="hover" class="cursor-p dash-card" style="padding: 10px">
                    <div class="">产品库存</div>
                    <div class="fz-lg text-right ">
                        <span>50</span>
                        <span class="fz-sm" style="margin-left: 2px;">件</span>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "DashBoardTopCards",
        components: {},
        props: {},
        data() {
            return {}
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DashBoardTopCards {
        .dash-card{
            div:nth-of-type(2){
                margin-top: 20px;
            }
        }
    }
</style>