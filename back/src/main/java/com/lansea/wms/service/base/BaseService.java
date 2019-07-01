package com.lansea.wms.service.base;

import com.lansea.wms.service.ApproveLogService;
import com.lansea.wms.service.CommonService;
import com.lansea.wms.service.PageService;
import com.lansea.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    protected PageService pageService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ApproveLogService approveLogService;

    @Autowired
    protected CommonService commonService;
}
