package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.ReservoirMapper;
import com.lansea.wms.mapper.WarehouseMapper;
import com.lansea.wms.model.Reservoir;
import com.lansea.wms.model.Warehouse;
import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.rcache.DflCache;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping(value = "/warehouse")
@Api(description = "")
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

}
