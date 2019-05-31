import AjaxUtil from "@/util/AjaxUtil";
import Api from "@/assets/api/Api";

export default class ImageModel {
    id = 0;
    name = '';
    url = '';
    user_id = '';
    full_url = '';
    create_time = '';

    static updateImage(bs64) {
        return AjaxUtil.request(Api.image.uploadBase64, {
            img: bs64
        });
    }
}