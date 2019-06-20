package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.MoveMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/move")
@Api(description = "出入库记录")
public class MoveController extends BaseController {

    @Autowired
    MoveMapper moveMapper;

    @GetMapping(value = "/select_by_stock_entry_id")
    @ApiOperation(value = "根据出入库单号获取")
    Result selectByStockEntryId(Integer stockEntryId) {
        return Result.success(
                moveMapper.selectByStockEntryId(stockEntryId)
        );
    }

}
