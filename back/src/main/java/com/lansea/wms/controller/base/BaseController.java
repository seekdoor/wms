package com.lansea.wms.controller.base;

import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import com.lansea.wms.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Autowired
    public UserService userService;

    @Autowired
    public PageService pageService;

    @Autowired
    public ValidateService validateService;

}
