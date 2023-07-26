package com.hsbc.insh.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClientInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 客户类型
     */
    private String clientType;

    /**
     * 描述
     */
    private String description;

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
    private String create_by;

    /**
     * 更新用户
     */
    private String update_by;


}
