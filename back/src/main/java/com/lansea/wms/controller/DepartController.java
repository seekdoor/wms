package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.DepartMapper;
import com.lansea.wms.model.Depart;
import com.lansea.wms.service.DepartService;
import com.lansea.wms.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depart")
@Api(description = "部门管理")
public class DepartController {

    @Autowired
    DepartMapper departMapper;

    @Autowired
    PageService pageService;

    @Autowired
    DepartService departService;

    @GetMapping("/list")
    @ApiOperation(value = "部门列表")
    Result list(Depart depart) {
        pageService.setPaginate();
        List<Depart> list = departMapper.selectWhere(depart, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping("/get_by_id")
    @ApiOperation(value = "根据id查找部门")
    Result getById(Integer id) {
        return Result.success(departMapper.findById(id));
    }


}
