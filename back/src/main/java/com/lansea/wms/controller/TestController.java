package com.lansea.wms.controller;

import com.lansea.wms.entity.Result;
import com.lansea.wms.util.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
public class TestController {

    @RequestMapping("/test1")
    Result beanUtil() {
        return Result.success(MD5Util.getMD5("123456"));
    }

}
