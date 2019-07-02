package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.form.StockEntryDeliveryFinishForm;
import com.lansea.wms.mapper.DeliveryMapper;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.StockEntry;
import com.lansea.wms.service.NumberCreateService;
import com.lansea.wms.service.StockEntryService;
import com.lansea.wms.util.StringUtil;
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
    @ApiOperation(value = "新增出入库单")
    Result insert(@Validated @RequestBody StockEntry stockEntry, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        stockEntry.setCreateUidToLoginUser(userService);
        stockEntry.setNumber(numberCreateService.create(
                stockEntry.getType() == 1 ? "stock_in" : "stock_out"
        ));
        stockEntryService.insert(stockEntry);
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
        Integer num = stockEntryService.delete(form.getIds());
        return Result.successDelete(num);
    }

    @Autowired
    StockEntryService stockEntryService;

    @PostMapping(value = "/submit")
    @ApiOperation(value = "/提交审核")
    Result submit(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody StockEntry form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        StockEntry stockEntry = stockEntryMapper.findById(form.getId());
        Integer oldStatus = stockEntry.getStatus();
        if (oldStatus != 1 && oldStatus != 4) {
            return Result.error("非法！！");
        }
        if (stockEntry.getMoveCount() < 1) {
            return Result.error("请添加明细！");
        }
        stockEntryService.submit(stockEntry);
        return Result.success("提交成功");
    }

    @PostMapping(value = "/approve")
    @ApiOperation(value = "审批")
    Result approve(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody StockEntry form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        StockEntry stockEntry = stockEntryMapper.findById(form.getId());
        if (stockEntry.getMoveCount().equals(0)) {
            return Result.error("请添加订单明细！");
        }
        Integer oldStatus = stockEntry.getStatus();
        if (!oldStatus.equals(2)) {
            return Result.error("订单状态错误！");
        }
        Integer nowStatus = form.getStatus();
        if (!nowStatus.equals(3) && !nowStatus.equals(4)) {
            return Result.error("非法！！");
        }
        if (nowStatus == 4 && StringUtil.isBlank(form.getRejectRemark())) {
            return Result.error("驳回理由不能为空");
        } else {
            stockEntry.setRejectRemark(form.getRejectRemark());
        }
        stockEntry.setStatus(form.getStatus());
        stockEntryService.approve(stockEntry);
        return Result.success("完成审批");
    }

    @PostMapping(value = "/finish")
    @ApiOperation(value = "完成订单")
    Result finish(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody StockEntry form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        StockEntry stockEntry = stockEntryMapper.findById(form.getId());
        if (!stockEntry.getStatus().equals(3)) {
            return Result.error("审批尚未通过！");
        }
        stockEntryService.finish(stockEntry);
        return Result.success("完成订单");
    }

    @Autowired
    DeliveryMapper deliveryMapper;

    @PostMapping(value = "/delivery_finish")
    @ApiOperation(value = "完成并发货")
    Result deliveryFinish(@RequestBody StockEntryDeliveryFinishForm form) {

        String sRes = validateService.validate(form.getStockEntry());
        if (sRes != null) {
            return Result.error(sRes);
        }
        StockEntry stockEntry = stockEntryMapper.findById(form.getStockEntry().getId());
        if (stockEntry.getStatus() != 3) {
            return Result.error("请审核完成后重试！");
        }
        if (form.getNeedDelivery() == 1) {
            form.getDelivery().setStockEntryId(form.getStockEntry().getId());
            String res = validateService.validate(form.getDelivery());
            if (res != null) {
                return Result.error(res);
            }
            stockEntryService.deliveryFinish(form);
        } else {
            stockEntryService.finish(form.getStockEntry());
        }
        return Result.success("完成发货成功");
    }

}
