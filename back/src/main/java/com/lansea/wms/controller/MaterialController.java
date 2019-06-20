package com.lansea.wms.controller;


import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.MaterialMapper;
import com.lansea.wms.model.Material;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping(value = "/material")
@Api(description = "物料接口")
public class MaterialController extends BaseController {

    @Autowired
    MaterialMapper materialMapper;

    @GetMapping(value = "/list")
    @ApiOperation(value = "分页条件查询")
    Result list(Material material) {
        pageService.setPaginate();
        List<Material> list = materialMapper.selectWhere(material, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping(value = "/get_by_id")
    @ApiOperation(value = "根据 id 获取")
    Result getById(Integer id) {
        return Result.success(materialMapper.findById(id));
    }

    @PostMapping(value = "/insert")
    @ApiOperation(value = "新增物料")
    Result insert(@Validated @RequestBody Material material, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        material.setCreateUidToLoginUser(userService);
        materialMapper.insert(material);
        return Result.success("新增成功");
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改物料")
    Result update(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Material material, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        material.setUpdateUidToLoginUser(userService);
        materialMapper.update(material);
        return Result.success("更新成功");
    }

    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除物料")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = materialMapper.delete(form.getIds());
        return Result.successDelete(num, "物料信息");
    }

    @GetMapping(value = "/select_by_types")
    @ApiOperation(value = "根据物料类型获取列表")
    Result selectByType(String types) {
        return Result.success(materialMapper.selectByTypes(types));
    }

}
