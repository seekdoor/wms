package com.lansea.wms.model;


import com.lansea.wms.entity.ValidClass;
import com.lansea.wms.enums.MaterialTypeEnum;
import com.lansea.wms.model.base.BaseUser;
import com.lansea.wms.util.EnumUtil;
import com.lansea.wms.validate.CodeValidate;
import com.lansea.wms.validate.NameValidate;
import com.lansea.wms.validate.RemarkValidate;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Repository
@ApiModel(value = "Material", description = "成产原料")
public class Material extends BaseUser {

    @Min(value = 1, message = "id 非法", groups = {ValidClass.EditForm.class})
    private Integer id;

    @CodeValidate
    private String code;

    @NameValidate
    private String name;

    @Min(value = 1, message = "请选择类别")
    private Integer type;

    @Min(value = 1, message = "请选择分类")
    private Integer categoryId;

    @Min(value = 1, message = "请选择单位")
    private Integer unitId;

    @Max(value = 100000, message = "安全库存取值为 0-100000 之间")
    private Integer safeNum;

    @Max(value = 100000, message = "有效天数取值为 0-100000 之间")
    private Integer validDay;

    @RemarkValidate
    private String remark;


    private String categoryName;
    private String unitName;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(Integer safeNum) {
        this.safeNum = safeNum;
    }

    public Integer getValidDay() {
        return validDay;
    }

    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        MaterialTypeEnum e = EnumUtil.valueOf(MaterialTypeEnum.class, type);
        if (e == null) {
            return "";
        }
        return e.getValue();
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
