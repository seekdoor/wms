<template>
    <div class="StockEntryPreviewDialog">
        <el-dialog
                :visible="visible"
                @update:visible="showDialog"
                :modal-append-to-body="true"
                width="1000px"
                custom-class="full-height-dialog"
                :append-to-body="true"
        >
            <div class="flex-box flex-center" slot="title">
                <div class="mr-md">{{`${typeText}单预览 ${stockEntry.number}`}}</div>
                <el-button-mini
                        icon="iconfont icon-print"
                        @click.native="clickPrintButton"
                >打印
                </el-button-mini>
            </div>
            <iframe style="width: 100%;border:none; height: 100%;"
                    ref="iframe"
            ></iframe>
            <div class="" v-show="false" ref="infoBlock">

                <div class="" style="">
                    <div class="" style="font-size: 20pt;margin-bottom: 20pt;text-align: center;">
                        {{typeText}}单
                    </div>
                    <table style="border-collapse: collapse;text-align: center;font-size: 11pt;width: 100%;"
                           border="true">
                        <thead>
                        <tr class="tr-iframe">
                            <th :style="tdStyle">{{ typeText }}单号</th>
                            <th :style="tdStyle">订单号</th>
                            <th :style="tdStyle">公司编号</th>
                            <th :style="tdStyle">公司名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="tr-iframe">
                            <td :style="tdStyle">{{stockEntry.number}}</td>
                            <td :style="tdStyle">{{stockEntry.orderNumber}}</td>
                            <td :style="tdStyle">{{stockEntry.companyCode}}</td>
                            <td :style="tdStyle">{{stockEntry.companyName}}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="" style="font-size: 12pt;text-align: center;margin: 4pt 0 2pt 0">
                        明细
                    </div>
                    <table style="border-collapse: collapse;text-align: center;font-size: 11pt;width: 100%;"
                           border="true">
                        <thead>
                        <tr class="tr-iframe">
                            <th width="300" :style="tdStyle">物料编号</th>
                            <th width="200" :style="tdStyle">物料名称</th>
                            <th width="160" :style="tdStyle">计划数量</th>
                            <th width="260" :style="tdStyle">实际数量</th>
                            <th width="260" :style="tdStyle">货架编号</th>
                            <th width="260" :style="tdStyle">货架名称</th>
                            <th width="260" :style="tdStyle">状态</th>
                            <th width="260" :style="tdStyle">审核人</th>
                            <th width="260" :style="tdStyle">审核时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="tr-iframe"
                            v-for="(item, i) in moves"
                            :key="item.id"
                        >
                            <td :style="tdStyle">{{item.materialCode}}</td>
                            <td :style="tdStyle">{{item.materialName}}</td>
                            <td :style="tdStyle">{{item.planQuantity}}</td>
                            <td :style="tdStyle">{{item.quantity}}</td>
                            <td :style="tdStyle">{{item.stockCode}}</td>
                            <td :style="tdStyle">{{item.stockName}}</td>
                            <td :style="tdStyle">{{ statusEnum[item.status] }}</td>
                            <td :style="tdStyle">{{item.approveUserName }}</td>
                            <td :style="tdStyle">{{item.approveTime }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import PrintUtil from "@/util/PrintUtil";
    import StockEntryModel from "@/project/model/StockEntryModel";
    import Api from "@/assets/api/Api";
    import ApproveEnum from "@/project/enum/ApproveEnum";
    import ElButtonMini from "@/components/common/button/ElButtonMini";

    export default {
        name: "StockEntryPreviewDialog",
        components: {ElButtonMini},
        props: {
            visible: {
                default: false
            },
            type: {
                default: 1,
            },
            editId: {
                default: 0
            }
        },
        data() {
            return {
                stockEntry: new StockEntryModel(),
                moves: [],
                loading: false,
                statusEnum: ApproveEnum[this.type === 1 ? 'dictIn' : 'dictOut']
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            showDialog(v) {
                this.$emit('update:visible', v);
            },
            refreshData() {
                this.stockEntry = new StockEntryModel();
                this.moves = [];
                this.loading = true;
                this.$ajax.request(Api.stockEntry.getById, {
                    id: this.editId
                }).then(resp => {
                    this.stockEntry = resp;
                    return this.$ajax.request(Api.move.selectByStockEntryId, {
                        stockEntryId: this.editId
                    });
                }).then(resp => {
                    this.moves = resp;
                    this.setDoc();
                }).finally(() => this.loading = false);
            },
            setDoc() {
                this.$nextTick(() => {
                    PrintUtil.setDoc(this.$refs.infoBlock.innerHTML, this.$refs.iframe);
                });
            },
            clickPrintButton() {
                this.$nextTick(() => {
                    PrintUtil.print(this.$refs.iframe);
                })
            }
        },
        watch: {
            visible(v) {
                if (v) {
                    this.$nextTick(() => {
                        this.refreshData();
                    })
                }
            },

        },
        computed: {
            typeText() {
                return this.type === 1 ? '入库' : '出库';
            },
            tdStyle() {
                return {
                    'padding': '3pt 1pt'
                }
            }
        },


    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .StockEntryPreviewDialog {
    }
</style>