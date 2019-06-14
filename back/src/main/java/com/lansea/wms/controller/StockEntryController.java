package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.StockEntryMapper;
import com.lansea.wms.model.StockEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock_entry")
public class StockEntryController extends BaseController {

    @Autowired
    StockEntryMapper stockEntryMapper;


    Result list(StockEntry stockEntry) {
        pageService.setPaginate();
        List<StockEntry> list = stockEntryMapper.selectWhere(stockEntry, pageService.createSort());
        return Result.successPage(list);
    }

}
