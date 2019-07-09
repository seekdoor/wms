<template>
    <div id="app">
        <router-view class="root-router"></router-view>
        <el-dialog
                :visible.sync="states.layout.errorWindowVisible"
                title="错误信息"
                :lock-scroll="false"
                :append-to-body="true"
                :fullscreen="true"
        >
            <div class="" v-html="states.info.ajaxErrorInfo"></div>
        </el-dialog>
    </div>
</template>

<script>
    import AppBackIndex from "@/components/AppBackIndex";
    import StatesContainer from "@/project/StatesContainer";

    export default {
        name: 'app',
        components: {
            AppBackIndex,
        },
        data() {
            return {
                states: StatesContainer,
            }
        },
        mounted() {
            console.log(process.env.NODE_ENV);
        }
    }
</script>

<style lang="less" scoped>
    #app {
        height: 100%;
        width: 100%;
    }
</style>
