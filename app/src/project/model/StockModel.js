import BaseUser from "@/project/model/base/BaseUser";

export default class StockModel extends BaseUser {

    id = 0;
    code = '';
    name = '';
    remark = '';
    warehouseId = 0;
    reservoirId = 0;

    warehouseName = '' ;
    reservoirName = '' ;

}