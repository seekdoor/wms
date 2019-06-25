import BaseUserBetweenTime from "@/project/model/base/BaseUserBetweenTime";

export default class StockEntryModel extends BaseUserBetweenTime {


    id = 0;
    number = '';
    orderNumber = '';
    categoryId = 0;
    companyId = 0;
    status = 0;
    type = 0;
    remark = '';

    moveCount = 0;

    cateogryName = '';
    companyName = '';
    companyCode = '';

    approveUid = 0;
    approveTime = '';
    approveUserName = '';

    rejectRemark = '';

    deliveryId = 0;

    constructor(type = 0) {
        super();
        this.type = type;
    }

// private Integer id;
    // private String number;
    // private String orderNumber;
    // private Integer categoryId;
    // private Integer companyId;
    // private Integer status;
    // private Integer type;
    // private String remark;
    //
    // private Integer moveCount;

}