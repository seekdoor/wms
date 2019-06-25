<template>
    <div class="ApproveTags disp-ib">
        <el-tag
                effect="dark"
                :type="statusEnum.colors[statusUse]"
        >
            {{ statusEnum[isStockIn?'dictIn' : 'dictOut'][statusUse] }}
        </el-tag>
    </div>
</template>

<script>
    import ApproveEnum from "@/project/enum/ApproveEnum";

    export default {
        name: "ApproveTags",
        components: {},
        props: {
            status: {
                default: 1
            },
            isStockIn: {
                default: true
            }
        },
        data() {
            return {
                statusEnum: ApproveEnum
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
        },
        watch: {},
        computed: {
            statusUse() {
                return this.status || 1;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .ApproveTags {
    }
</style>