package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileInfoMapper {


    boolean insertFile(FileInfo fileInfo);

    List<FileInfo> queryFile(FileInfo fileInfo);

    boolean deleteFile(FileInfo fileInfo);

    List<String> getProducts(String fileType);
}
