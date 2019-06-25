import StockEntryModel from "@/project/model/StockEntryModel";
import DeliveryModel from "@/project/model/DeliveryModel";

export default class StockEntryDeliveryFinishFormModel {

    stockEntry = new StockEntryModel();
    delivery = new DeliveryModel();
    needDelivery = 1;

}