package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Repository
@ApiModel(value = "Unit", description = "单位模型")
public class Unit {

    @ApiModelProperty(value = "ID")
    @Min(value = 1, message = "id 非法", groups = {ValidClass.AddForm.class})
    private Integer id;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
