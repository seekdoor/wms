import BaseUser from "@/project/model/base/BaseUser";

export default class BaseWarehouseAndUser extends BaseUser {

    warehouseId = 0;
    reservoirId = 0;
    stockId = 0;

    warehouseName = '';
    reservoirName = '';
    stockName = '';

    warehouseCode = '';
    reservoirCode = '';
    stockCode = '';

}