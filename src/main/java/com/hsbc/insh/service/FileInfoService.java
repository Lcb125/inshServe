package com.hsbc.insh.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsbc.insh.common.entity.FileInfo;
import com.hsbc.insh.common.page.PageRequest;
import com.hsbc.insh.common.page.PageResponse;
import com.hsbc.insh.common.util.PageUtils;
import com.hsbc.insh.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInfoService {

    private final static String VIEWURL = "http://150.158.173.37:8088/api/file/viewFiles?fileType=";

    @Autowired
    FileInfoMapper fileInfoMapper;

    public boolean insertFile(FileInfo fileInfo) {
        return fileInfoMapper.insertFile(fileInfo);
    }

    public List<FileInfo> queryFile(FileInfo fileInfo) {
        return fileInfoMapper.queryFile(fileInfo);
    }

    public boolean deleteFile(FileInfo fileInfo) {
        return fileInfoMapper.deleteFile(fileInfo);
    }

    public PageResponse findPage(PageRequest pageRequest){
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }


    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<FileInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(pageRequest.getFileName());
        fileInfo.setFileType(pageRequest.getFileType());
        List<FileInfo> fileInfos = fileInfoMapper.queryFile(fileInfo);
        for (FileInfo info : fileInfos){
            info.setViewUrl(VIEWURL+info.getFileType()+"&fileName="+info.getFileName());
        }
        return new PageInfo<FileInfo>(fileInfos);
    }
}
