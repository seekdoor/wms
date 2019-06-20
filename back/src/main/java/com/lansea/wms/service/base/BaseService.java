package com.lansea.wms.service.base;

import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    PageService pageService;

    @Autowired
    UserService userService;
}
