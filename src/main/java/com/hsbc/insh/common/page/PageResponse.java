package com.hsbc.insh.common.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> listInfo;

    /**
     * 用户类型
     */
    private String userType;

}
