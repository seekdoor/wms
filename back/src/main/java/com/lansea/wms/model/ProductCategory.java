package com.lansea.wms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

@Repository
@ApiModel(value = "ProductCategory", description = "产品类别")
public class ProductCategory {
    private Integer id;
    private Integer pid;
    private String name;
}
