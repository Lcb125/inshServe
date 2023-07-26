package com.hsbc.insh.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户级别
     */
    private String userLevel;

    /**
     * 手机号
     */
    private String phone_number;

    /**
     * 描述
     */
    private String description;

    /**
     * 客户id
     */
    private int clientCode;

    /**
     * 用户状态
     */
    private String status;

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
