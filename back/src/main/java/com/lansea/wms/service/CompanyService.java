package com.lansea.wms.service;

import com.lansea.wms.mapper.CompanyMapper;
import com.lansea.wms.mapper.UserMapper;
import com.lansea.wms.model.Company;
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
    public Company addUser(Company company) {
        try {
            company.setCreateUser(userMapper.findById(company.getCreateUid()));
            if (company.getApproveUid() != 0) {
                company.setApproveUser(userMapper.findById(company.getApproveUid()));
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
            addUser(company);
        }
        return companies;
    }

}
