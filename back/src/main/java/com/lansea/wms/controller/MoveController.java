package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.model.Move;
import com.lansea.wms.service.MoveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;


@RestController
@RequestMapping(value = "/move")
@Api(description = "出入库记录")
public class MoveController extends BaseController {

    @Autowired
    MoveMapper moveMapper;

    @Autowired
    MoveService moveService;

    @GetMapping(value = "/select_by_stock_entry_id")
    @ApiOperation(value = "根据出入库单号获取")
    Result selectByStockEntryId(Integer stockEntryId) {
        return Result.success(
                moveMapper.selectByStockEntryId(stockEntryId)
        );
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增出入库信息")
    Result insert(@Validated @RequestBody Move move, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        moveService.insertMove(move);
        return Result.success("添加成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除出入库信息")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = moveService.deleteByIds(form);
        return Result.successDelete(num);
    }

    @GetMapping(value = "/get_by_id")
    @ApiOperation(value = "根据id查询")
    Result getById(Integer id) {
        return Result.success(moveMapper.findById(id));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改出入库信息")
    Result update(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Move move, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        moveService.updateMove(move);
        return Result.success("修改成功");
    }

}
