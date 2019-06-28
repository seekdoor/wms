package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.MoveMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.Move;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.MoveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.List;


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

    @GetMapping(value = "/select_by_stock_trans_id")
    @ApiOperation(value = "根据移位单号获取")
    Result selectByStockTransId(Integer stockTransId) {
        return Result.success(
                moveMapper.selectByStockTransId(stockTransId)
        );
    }

    @Autowired
    StockEntryMapper stockEntryMapper;

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增出入库信息")
    Result insert(@Validated @RequestBody Move form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        if (form.getType() != 3 && form.getPlanQuantity().equals(new BigDecimal("0"))) {
            return Result.error("计划数量不能为0");
        }
        if (form.getType() == 3) {
            form.setPlanQuantity(form.getQuantity());
        }
        if (moveService.checkApproved(form)) {
            return Result.error("订单已被审批!");
        }
        moveService.insertMove(form);
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
        if (moveService.checkApproved(move)) {
            return Result.error("订单已被审批!");
        }
        moveService.updateMove(move);
        return Result.success("修改成功");
    }

    @GetMapping(value = "/stock_in_list")
    @ApiOperation(value = "入库记录")
    Result stockInList(Move move) {
        move.setType(1);
        pageService.setPaginate();
        List<Move> list = moveMapper.selectWhere(move, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/stock_out_list")
    @ApiOperation(value = "出库记录")
    Result stockOutList(Move move) {
        move.setType(2);
        pageService.setPaginate();
        List<Move> list = moveMapper.selectWhere(move, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/trans_list")
    @ApiOperation(value = "移位记录")
    Result transList(Move move){
        move.setType(3);
        pageService.setPaginate();
        List<Move> list = moveMapper.selectWhere(move, pageService.createSort());
        return Result.successPage(list);
    }


}
