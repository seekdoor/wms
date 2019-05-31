export default {


    /**
     * 文件读入改变
     * @param event
     * @returns {Promise<any>}
     */
    fileInputChange(event) {
        return new Promise((resolve, reject) => {
            let image = event.target.files[0];
            if ( !image.type || !/image/.test(image.type)) {
                return reject('请选择图片文件！');
            }
            let reader = new FileReader();
            reader.onload = (evt) => {
                return resolve(evt.target.result);
            };
            reader.readAsDataURL(image);
        })
    }

}