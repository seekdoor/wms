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
                let c = (v + '').match(/[1-9]\d{0,8}/);
                this.valueUse = c ? c[0] : 0;
                this.$emit('input', this.valueUse);
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