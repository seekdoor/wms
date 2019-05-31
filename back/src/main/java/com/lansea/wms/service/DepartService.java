package com.lansea.wms.service;

import com.lansea.wms.mapper.DepartMapper;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.Depart;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DepartMapper departMapper;

    /**
     * 添加用户
     * @param depart
     * @return
     */
    public Depart addUser(Depart depart) {
        depart.setCreateUser(userMapper.findById(depart.getCreateUid()));
        if (depart.getUpdateUid() != 0) {
            depart.setUpdateUser(userMapper.findById(depart.getUpdateUid()));
        }
        return depart;
    }

    /**
     * 添加用户
     * @param departs
     * @return
     */
    public List<Depart> addUsers(List<Depart> departs){
        for( Depart depart: departs){
            addUser(depart);
        }
        return departs;
    }
}
