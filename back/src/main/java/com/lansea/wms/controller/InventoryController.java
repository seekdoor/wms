package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.InventoryMapper;
import com.lansea.wms.model.Inventory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/inventory")
@Api(description = "库存管理")
public class InventoryController extends BaseController {

    @Autowired
    InventoryMapper inventoryMapper;

    @GetMapping(value = "/select_all_material_ids")
    @ApiOperation(value = "获取所有物料id")
    Result selectAllMaterialIds() {
        return Result.success(inventoryMapper.selectAllMaterialIds());
    }

    @GetMapping(value = "/select_all_stock_ids")
    @ApiOperation(value = "获取所有货架id")
    Result selectAllStockIds() {
        return Result.success(inventoryMapper.selectAllStockIds());
    }

    @GetMapping(value = "/select_all_stock_ids_by_mtl_ids")
    @ApiOperation(value = "根据的材料id获取货架id列表")
    Result selectAllStockIdsByMtlIds(String materialIds) {
        return Result.success(inventoryMapper.selectAllStockIdsByMtlIds(materialIds));
    }

    @GetMapping(value = "/get_by_mtl_and_stk")
    @ApiOperation(value = "根据材料id和货架id查询")
    Result getByMtlAndStk(Inventory inventory) {
        return Result.success(inventoryMapper.findByMtlAndStk(inventory));
    }

    @GetMapping(value = "/select_all_mtl_ids_by_stk_id")
    @ApiOperation(value = "根据货架id获取物料id列表")
    Result selectAllMtlIdsByStkId(Integer stkId) {
        return Result.success(inventoryMapper.selectAllMtlIdsByStkId(stkId));
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "条件查询分页列表")
    Result list(Inventory inventory) {
        pageService.setPaginate();
        List<Inventory> list = inventoryMapper.selectWhere(inventory, pageService.createSort());
        return Result.successPage(list);
    }


}
