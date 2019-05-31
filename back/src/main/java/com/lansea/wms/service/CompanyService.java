package com.lansea.wms.service;

import com.lansea.wms.mapper.CompanyMapper;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.Company;
import com.lansea.wms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CompanyMapper companyMapper;


    /**
     * 为 Company 添加用户信息
     *
     * @param company 合作伙伴
     * @return
     */
    public Company addUsers(Company company) {
        try {
            company.setCreator(userMapper.findById(company.getCreatorId()));
            if (company.getApproverId() != 0) {
                company.setApprover(userMapper.findById(company.getApproverId()));
            }
        } catch (Exception ignore) {
        }
        return company;
    }

    /**
     * 为 Company 列表批量天际用户信息
     *
     * @param companies 合作伙伴列表
     * @return
     */
    public List<Company> addUsers(List<Company> companies) {
        for (Company company : companies) {
            addUsers(company);
        }
        return companies;
    }

}
