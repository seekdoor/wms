package com.lansea.wms.form;

import com.lansea.wms.entity.SerializeBase;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeleteIdsForm extends SerializeBase {

    @NotNull(message = "ids 不能为空")
    @NotBlank(message = "ids 不能为空")
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
