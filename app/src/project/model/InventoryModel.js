import BaseWarehouseAndUser from "@/project/model/base/BaseWarehouseAndUser";

export default class InventoryModel extends BaseWarehouseAndUser {

    id = 0;
    materialId = 0;
    quantity = 0;
    lastInTime = "";
    lastOutTime = "";
    lastInQuantity = 0;
    lastOutQuantity = 0;
    materialCode = '';
    materialName = '';
    mtlUnitName = '';

}