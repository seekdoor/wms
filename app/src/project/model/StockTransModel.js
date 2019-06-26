import BaseWareUserTime from "@/project/model/base/BaseWareUserTime";

export default class StockTransModel extends BaseWareUserTime {

    id = 0;
    number = '';
    status = 0;
    remark = '';
    warehouseTid = 0;
    reservoirTid = 0;
    stockTid = 0;
    warehouseNameTo = '';
    reservoirNameTo = '';
    stockNameTo = '';
    warehouseCodeTo = '';
    reservoirCodeTo = '';
    stockCodeTo = '';

    moveCount = 0;
    approveUid = 0;
    approveUserName = '';
    rejectRemark = '';

}