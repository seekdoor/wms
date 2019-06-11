<template>
    <material-manager-content class="MaterialManagerView "
                              :type="1"
    ></material-manager-content>
</template>

<script>
    import MaterialManagerContent from "@/components/page-content/material/content/MaterialManagerContent";

    export default {
        name: "MaterialManagerView",
        components: {
            MaterialManagerContent,
        },
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

    .MaterialManagerView {
    }
</style>