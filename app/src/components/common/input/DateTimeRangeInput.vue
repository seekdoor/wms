<template>
    <div class="DateTimeRangeInput">
        <el-date-picker
                :value="dateRange"
                @input="change"
                type="daterange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :style="pickerStyle"
                align="right">
        </el-date-picker>
    </div>
</template>

<script>
    import DateTimeUtil from "@/util/DateTimeUtil";

    export default {
        name: "DateTimeRangeInput",
        components: {},
        props: {
            startTime: {
                default: '2019-01-01'
            },
            endTime: {
                default: '2019-01-02'
            },
            width: {
                default: '260px'
            }
        },
        data() {
            return {
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, '']);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, '']);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, '']);
                        }
                    }],
                },
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            change(v) {
                if (!v || v.length < 2) {
                    this.$emit('update:start-time', '');
                    this.$emit('update:end-time', '');
                    return;
                }
                this.$emit('update:start-time', v[0] === '' ? '' : DateTimeUtil.dateFormat(v[0]));
                this.$emit('update:end-time', v[1] === '' ? '' : DateTimeUtil.dateFormat(v[1]));
            }
        },
        watch: {
            startTime(v) {
                console.log(v);
            }
        },
        computed: {
            dateRange() {
                return [new Date(this.startTime), new Date(this.endTime)]
            },
            pickerStyle() {
                return {
                    'width': this.width,
                }
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .DateTimeRangeInput {
    }
</style>