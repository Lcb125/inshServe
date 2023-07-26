package com.hsbc.insh.common.entity;

import lombok.Data;

@Data
public class AddDBReq {

    private String folder;

    private String filename;

    private String product;

    private String descript;

    private String url;
}
