<template>
    <div class="DateTimeInput">

    </div>
</template>

<script>
    export default {
        name: "DateTimeInput",
        components: {},
        props: {
            timestamp : {
                default : 0
            }
        },
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
    @import "~style/var.less";

    .DateTimeInput {
    }
</style>