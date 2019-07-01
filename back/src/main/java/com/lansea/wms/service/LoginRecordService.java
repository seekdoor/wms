package com.lansea.wms.service;

import com.lansea.wms.mapper.LoginRecordMapper;
import com.lansea.wms.model.LoginRecord;
import com.lansea.wms.model.User;
import com.lansea.wms.service.base.BaseService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginRecordService extends BaseService {

    @Autowired
    LoginRecordMapper loginRecordMapper;

    @Autowired
    HttpServletRequest request;

    /**
     * 根据 type 类型添加
     * 自动追加其他需要字段
     *
     * @param type 类型 1：登录 2：登出
     * @return
     */
    public Integer addByType(Integer type) {
        LoginRecord log = new LoginRecord();
        log.setType(type);
        log.setIp(commonService.getIpAddr());
        log.setUid(userService.getLoginUser().getId());
        return loginRecordMapper.insert(log);
    }

    /**
     * 添加登录日志
     *
     * @return
     */
    public Integer addLogin(User user) {
        LoginRecord log = new LoginRecord();
        log.setType(1);
        log.setIp(commonService.getIpAddr());
        log.setUid(user.getId());
        return loginRecordMapper.insert(log);
    }

    /**
     * 添加退出日志
     *
     * @return
     */
    public Integer addLogout() {
        return addByType(2);
    }

}
