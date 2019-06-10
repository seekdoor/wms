package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.ReservoirMapper;
import com.lansea.wms.mapper.StockMapper;
import com.lansea.wms.mapper.WarehouseMapper;
import com.lansea.wms.model.Reservoir;
import com.lansea.wms.model.Stock;
import com.lansea.wms.model.Warehouse;
import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping(value = "/warehouse")
@Api(description = "仓库相关接口")
public class WarehouseController {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Autowired
    UserService userService;

    @Autowired
    PageService pageService;

    @GetMapping("/war_list")
    @ApiOperation(value = "仓库分页列表")
    Result warList(Warehouse warehouse) {
        pageService.setPaginate();
        List<Warehouse> list = warehouseMapper.selectWhere(warehouse, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/war_get_by_id")
    @ApiOperation(value = "根据id获取仓库")
    Result warGetById(Integer id) {
        return Result.success(warehouseMapper.findById(id));
    }

    @PostMapping(value = "/war_insert")
    @ApiOperation(value = "添加仓库")
    Result warInsert(@Validated @RequestBody Warehouse warehouse, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        warehouse.setCreateUid(userService.getLoginUser().getId());
        warehouseMapper.insert(warehouse);
        return Result.success("添加仓库成功");
    }

    @PostMapping(value = "/war_update")
    @ApiOperation(value = "修改仓库")
    Result warUpdate(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Warehouse warehouse, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        warehouse.setUpdateUid(userService.getLoginUser().getId());
        warehouseMapper.update(warehouse);
        return Result.success("修改仓库成功");
    }

    @PostMapping(value = "/war_delete")
    @ApiOperation(value = "删除仓库")
    Result warDelete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = warehouseMapper.deleteByIds(form.getIds());
        return Result.success("成功删除" + num + "条记录");
    }

    @GetMapping(value = "/war_select_all")
    @ApiOperation(value = "获取所有仓库")
    Result warSelectAll() {
        return Result.success(warehouseMapper.selectAll());
    }

    // 以下是库区

    @Autowired
    ReservoirMapper reservoirMapper;

    @GetMapping(value = "/res_list")
    @ApiOperation(value = "分页库区")
    Result resList(Reservoir reservoir) {
        pageService.setPaginate();
        List<Reservoir> list = reservoirMapper.selectWhere(reservoir, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/res_get_by_id")
    @ApiOperation(value = "根据id获取库区")
    Result resGetById(Integer id) {
        return Result.success(reservoirMapper.findById(id));
    }

    @PostMapping(value = "/res_insert")
    @ApiOperation(value = "添加库位")
    Result resInsert(@Validated @RequestBody Reservoir reservoir, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        reservoir.setCreateUid(userService.getLoginUser().getId());
        reservoirMapper.insert(reservoir);
        return Result.success("添加库位成功");
    }

    @PostMapping(value = "/res_update")
    @ApiOperation(value = "修改库位")
    Result resUpdate(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Reservoir reservoir, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        reservoir.setUpdateUid(userService.getLoginUser().getId());
        reservoirMapper.update(reservoir);
        return Result.success("修改库位成功");
    }

    @PostMapping(value = "/res_delete")
    @ApiOperation(value = "删除库位")
    Result resDelete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = reservoirMapper.deleteByIds(form.getIds());
        return Result.success("成功删除" + num + "条记录");
    }

    @GetMapping(value = "/res_all_by_warehouse_id")
    @ApiOperation(value = "根据仓库id查询")
    Result resAllByWarehouseId(Integer id) {
        return Result.success(reservoirMapper.selectByWarehouseId(id));
    }

    @GetMapping(value = "/res_select_all")
    @ApiOperation(value = "查询所有库位")
    Result resSelectAll() {
        return Result.success(reservoirMapper.selectAll());
    }

    // 以下是货架接口

    @Autowired
    StockMapper stockMapper;

    @GetMapping(value = "/stock_list")
    @ApiOperation(value = "分页货架列表")
    Result stockList(Stock stock) {
        pageService.setPaginate();
        List<Stock> list = stockMapper.selectWhere(stock, pageService.createSort());
        return Result.successPage(list);
    }


    @GetMapping(value = "/stock_get_by_id")
    @ApiOperation(value = "根据id查询货架")
    Result stockGetById(Integer id) {
        return Result.success(stockMapper.findById(id));
    }

    @PostMapping(value = "/stock_insert")
    @ApiOperation(value = "新增货架")
    Result stockInsert(@Validated @RequestBody Stock stock, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stock.setCreateUid(userService.getLoginUser().getId());
        stockMapper.insert(stock);
        return Result.success("新增成功");
    }

    @PostMapping(value = "/stock_update")
    @ApiOperation(value = "修改货架")
    Result stockUpdate(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Stock stock, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stock.setUpdateUid(userService.getLoginUser().getId());
        stockMapper.update(stock);
        return Result.success("修改成功");
    }

    @PostMapping(value = "/stock_delete")
    @ApiOperation(value = "/删除货架")
    Result stockDelete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = stockMapper.deleteByIds(form.getIds());
        return Result.success("共删除" + num + "条数据");
    }

    @GetMapping(value = "/stock_all_by_reservoir_id")
    @ApiOperation(value = "/根据 库位 id 查询")
    Result stockAllByReservoirId(Integer reservoirId) {
        return Result.success(stockMapper.selectByReservoirId(reservoirId));
    }

    @GetMapping(value = "/stock_select_all")
    @ApiOperation(value = "/查询所有货架")
    Result stockSelectAll() {
        return Result.success(stockMapper.selectAll());
    }

}
