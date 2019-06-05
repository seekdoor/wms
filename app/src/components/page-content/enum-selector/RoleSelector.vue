<template>
    <div class="RoleSelector">
        <el-select :value="valueUse" @input="change" placeholder="请选择角色">
            <el-option label="所有角色" :value="0" v-if="isFilter"></el-option>
            <el-option v-for="item in roles"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
            ></el-option>
        </el-select>
    </div>
</template>

<script>
    import Api from "@/assets/api/Api";

    export default {
        name: "RoleSelector",
        components: {},
        props: {
            value: {
                default: 0
            },
            isFilter:{
                default : false
            }
        },
        data() {
            return {
                roles: []
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                this.refreshList();
                this.change( this.isFilter ? 0 : null );
            },
            change(v) {
                this.$emit('input', v);
            },
            refreshList() {
                this.$ajax.request(Api.role_auth.roleAll)
                    .then(resp => {
                        this.roles = resp;
                    })
            }
        },
        watch: {},
        computed: {
            valueUse(){
                if( this.value ){
                    return this.value;
                }
                return  this.isFilter ? 0 : null;
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .RoleSelector {
    }
</style>