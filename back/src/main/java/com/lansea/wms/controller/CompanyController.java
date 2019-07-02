package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.form.DeleteIdsForm;
import com.lansea.wms.mapper.CompanyMapper;
import com.lansea.wms.model.Company;
import com.lansea.wms.service.CompanyService;
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
@RequestMapping(value = "/company")
@Api(description = "合作方公司管理")
public class CompanyController {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyService companyService;

    @Autowired
    PageService pageService;

    @Autowired
    UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "合作方列表")
    Result list(Company company) {
        pageService.setPaginate();
        List<Company> list = companyMapper.selectWhere(company, pageService.createSort());
        return Result.successPage(list);
    }

    @GetMapping("/get_by_id")
    @ApiOperation(value = "根据id查找")
    Result getById(@Validated({ValidClass.FindIdForm.class}) Company company, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        return Result.success(companyMapper.findById(company.getId()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增合作方")
    Result add(@Validated @RequestBody Company company, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        company.setCreateUid(userService.getLoginUser().getId());
        companyMapper.insert(company);
        return Result.success("新增合作方成功");
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改合作方信息")
    Result edit(@Validated({ValidClass.EditForm.class, Default.class}) @RequestBody Company company, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        company.setUpdateUidToLoginUser(userService);
        companyMapper.update(company);
        return Result.success("修改合作方成功");
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除合作方")
    Result delete(@Validated @RequestBody DeleteIdsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.errorByBindingResult(result);
        }
        Integer num = companyMapper.deleteByIds(form.getIds());
        return Result.success("成功删除 " + num + "条记录");
    }

    @GetMapping("/select_by_category")
    @ApiOperation(value = "根据 category 获取所有公司")
    Result selectByCategory(Integer category) {
        return Result.success(companyMapper.selectByCategory(category));
    }

}
