<template>
    <div class="AppLoginIndex">
        <login-dialog
            :visible="true"
        ></login-dialog>
    </div>
</template>

<script>
    import LoginDialog from "@/components/common/LoginDialog";
    export default {
        name: "AppLoginIndex",
        components: {LoginDialog},
        props: {},
        data() {
            return {}
        },
        mounted() {
            this.reload();
        },
        activated() {
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
    @import (reference) "~@/style/all.less";

    .AppLoginIndex {
    }
</style>