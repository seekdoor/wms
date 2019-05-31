package com.lansea.wms.model;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Repository;

@Repository
@ApiModel(value = "Warehouse", description = "仓库")
public class Warehouse {

    private Integer id;
    private Integer pid;
    private String code;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
