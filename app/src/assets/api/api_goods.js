export default {

    // 获取分类列表
    getCategoryList: ['/back/goods/getCategoryList', 'get'],
    // 添加商品分类
    addCategory: ['/back/goods/addCategory', 'post'],
    // 编辑商品分类
    editCategory: ['/back/goods/editCategory', 'post'],
    // 删除商品
    removeCategory: ['/back/goods/removeCategory', 'post'],

    // 商品列表
    goodsList: ['/back/goods/goodsList', 'get'],
    // 根据id 获取商品详情
    getGoodsById: ['/back/goods/getGoodsById', 'get'],
    // 添加商品
    addGoods: ['/back/goods/addGoods', 'post'],
    // 修改商品信息
    editGoods: ['/back/goods/editGoods', 'post'],
    // 删除商品
    removeGoods: ['/back/goods/removeGoods', 'post'],

    // 商品 sku 列表
    skuList: ['/back/goods/skuList', 'get'],
    // 添加 sku
    addSku: ['/back/goods/addSku', 'post'],
    // 编辑 sku
    editSku: ['/back/goods/editSku', 'post'],
    // 删除 sku
    removeSku: ['/back/goods/removeSku', 'post'],
    // 设置状态
    setStatus:['/back/goods/setStatus', 'post'] ,

}