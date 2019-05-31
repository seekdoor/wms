package com.lansea.wms.model;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Repository;

@Repository
@ApiModel(value = "ProductCategory", description = "产品分类")
public class ProductCategory {
    private Integer id;
    private Integer pid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
