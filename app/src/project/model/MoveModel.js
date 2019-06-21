import BaseUser from "@/project/model/base/BaseUser";

export default class MoveModel extends BaseUser {

    id = 0;
    type = 0;
    inventory_id = 0;
    inventory_tid = 0;
    company_id = 0;
    stock_entry_id = 0;
    happen_date = '';
    status = 0;

    materialId = 0;
    materialCode = '';
    materialName = '';
    materialCategoryName = '';
    materialUnitName = '';

    warehouseId = 0;
    reservoirId = 0;
    stockId = 0;

    warehouseName = '';
    reservoirName = '';
    stockName = '';

    warehouseIdTo = 0;
    reservoirIdTo = 0;
    stockIdTo = 0;

    warehousNameTo = '';
    reservoirNameTo = '';
    stockNameTo = '';

    planQuantity = 0;
    quantity = 0;

}