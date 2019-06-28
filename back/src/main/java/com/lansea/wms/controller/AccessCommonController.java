package com.lansea.wms.controller;

import com.lansea.wms.controller.base.BaseController;
import com.lansea.wms.entity.Result;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/access_common")
public class AccessCommonController extends BaseController {

    @Autowired
    UserMapper userMapper;

    Result logout(){
        return null;
    }

}
