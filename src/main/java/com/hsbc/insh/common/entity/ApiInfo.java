package com.hsbc.insh.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ApiInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 描述
     */
    private String description;

    /**
     * 访问token
     */
    private String apiKey;

    /**
     * 访问地址
     */
    private String endpoint;

    /**
     * 用户ID（外键关联）
     */
    private String userCode;

    /**
     * 用户状态
     */
    private int status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建用户
     */
    private String createBy;

    /**
     * 更新用户
     */
    private String updateBy;


}
