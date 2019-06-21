package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.NumberCreateMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.NumberCreate;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.NumberCreateService;
import com.lansea.wms.service.StockEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/stock_entry")
@Api(description = "出入库单接口")
public class StockEntryController extends BaseController {

    @Autowired
    StockEntryMapper stockEntryMapper;

    @GetMapping(value = "/list")
    @ApiOperation(value = "分类条件查询")
    Result list(StockEntry stockEntry) {
        pageService.setPaginate();
        List<StockEntry> list = stockEntryMapper.selectWhere(stockEntry, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/get_by_id")
    @ApiOperation(value = "根据id查找")
    Result getById(Integer id) {
        return Result.success(stockEntryMapper.findById(id));
    }

    @Autowired
    NumberCreateService numberCreateService;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增出库单")
    Result insert(@Validated @RequestBody StockEntry stockEntry, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stockEntry.setCreateUidToLoginUser(userService);
        stockEntry.setNumber(numberCreateService.create(
                stockEntry.getType() == 1 ? "stock_in" : "stock_out"
        ));
        stockEntryMapper.insert(stockEntry);
        return Result.success("新增成功");
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改出入库单")
    Result update(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody StockEntry stockEntry, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stockEntry.setUpdateUidToLoginUser(userService);
        stockEntryMapper.update(stockEntry);
        return Result.success("修改成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除出入库单")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = stockEntryMapper.delete(form.getIds());
        return Result.successDelete(num);
    }

    @Autowired
    StockEntryService stockEntryService;

    @PostMapping(value = "/update_status")
    @ApiOperation(value = "审批")
    Result updateStatus(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody StockEntry stockEntry, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stockEntryService.approve(stockEntry);
    }

}
