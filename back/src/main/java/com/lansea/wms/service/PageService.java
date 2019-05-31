package com.lansea.wms.service;


import com.github.pagehelper.PageHelper;
import com.lansea.wms.entity.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PageService {

    @Autowired
    HttpServletRequest request;

    /**
     * 设置分页基本信息
     */
    public void setPaginate() {
        int pageNum = 1;
        int pageSize = 20;
        String pageNumString = request.getParameter("pageNum");
        String pageSizeString = request.getParameter("pageSize");
        if (pageNumString != null) {
            pageNum = Integer.parseInt(pageNumString);
        }
        if (pageSizeString != null) {
            pageSize = Integer.parseInt(pageSizeString);
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 返回请求排序字段
     *
     * @return
     */
    public Sort createSort() {
        Sort sort = new Sort();
        sort.setSortField(request.getParameter("sortField"));
        sort.setSortOrder(request.getParameter("sortOrder"));
        return sort;
    }

}
