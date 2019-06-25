<template>
    <div class="ElInputInteger">
        <el-input
                v-model="valueUse"
        ></el-input>
    </div>
</template>

<script>
    export default {
        name: "ElInputInteger",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFloat: {
                default: false
            },
            min: {
                default: 0
            },
            max: {
                default: 99999999
            }
        },
        data() {
            return {
                valueUse: this.value
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
        },
        watch: {
            valueUse(v) {
                let regex = new RegExp('[1-9]\\d{0,8}');
                if (this.isFloat) {
                    regex = new RegExp('^\\d{1,8}\\.{0,1}\\d{0,2}');
                }
                let c = (v + '').match(regex);
                this.valueUse = c ? c[0] : 0;
                if (this.valueUse.length > 1 && [...this.valueUse][1] !== '.' && [...this.valueUse][0] === '0') {
                    this.valueUse = this.valueUse.slice(1);
                }
                let num = Number.parseFloat(this.valueUse);
                if (num < this.min) {
                    this.valueUse = this.min;
                }
                if (num > this.max) {
                    this.valueUse = this.max;
                }
                this.$emit('input', this.valueUse);
            },
            value(v) {
                this.valueUse = v;
            }
        },
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .ElInputInteger {
    }
</style>