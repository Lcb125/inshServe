package com.hsbc.insh.common.page;

import lombok.Data;

@Data
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 访问码
     */
    private int code;

    /**
     * 配置信息key
     */
    private String codeKey;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件分类
     */
    private String fileType;


}
