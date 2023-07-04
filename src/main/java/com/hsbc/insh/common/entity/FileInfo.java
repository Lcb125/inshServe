package com.hsbc.insh.common.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 所属部门
     */
    private String fileType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 上传用户
     */
    private String createBy;

}
