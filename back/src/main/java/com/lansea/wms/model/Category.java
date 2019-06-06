package com.lansea.wms.model;

import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.enums.CategoryTypeEnum;
import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.EnumUtil;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Min;

@Repository
@ApiModel(value = "Category", description = "类型字典")
public class Category extends BaseUser {

    @Min(value = 1, message = "id 非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @Min(value = 1, message = "请选择类型")
    private Integer type;

    @Length(min = 1, max = 40, message = "类型名称长度为 1-40")
    private String name;

    @Length(max = 500, message = "备注最大长度为 500")
    private String remark;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTypeName() {
        return EnumUtil.valueOf(CategoryTypeEnum.class, type).getValue();
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
