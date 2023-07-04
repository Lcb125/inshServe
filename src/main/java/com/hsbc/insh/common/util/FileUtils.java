package com.hsbc.insh.common.util;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.insh.common.Enum.RespEnum;
import com.hsbc.insh.common.entity.FileInfo;
import com.hsbc.insh.common.entity.ResponseResult;
import com.hsbc.insh.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Slf4j
@Component
public class FileUtils {

    private final static String PATH = "C:/codebase/文件上传";

    @Autowired
    FileInfoService fileInfoService;

    @PostMapping("/uploadFiles")
    public ResponseResult uploadFiles(HttpServletRequest request, String fileType)
    {
        // HttpServletRequest 转 multipartHttpServletRequest 用于获取附件
        log.info("fileType======"+fileType);


        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartFiles = multipartHttpServletRequest.getFiles("files");//获取附件

        String pathName = PATH + "/" +fileType + "/";//想要存储文件的地址
        for (MultipartFile file: multipartFiles){
            String originalFilename = file.getOriginalFilename();
            log.info("name========"+file.getName());
            log.info("originalFilename=========="+originalFilename);
//            保存文件
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(pathName+originalFilename);
                fos.write(file.getBytes()); // 写入文件
                log.info("文件上传成功");
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileType(fileType);
                fileInfo.setFileName(originalFilename);
                fileInfoService.insertFile(fileInfo);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("文件上传失败"+pathName);
            } finally {
                try {
                    assert fos != null;
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }




        return new ResponseResult(RespEnum.SUCCESS,"");
    }


    public ResponseResult deleteFiles(FileInfo fileInfo) {

        String fileType = fileInfo.getFileType();
        String fileName = fileInfo.getFileName();
        String file = PATH+"/"+fileType+"/"+fileName;
        File files = new File(file);
        if (!files.exists()) {
            log.info("删除文件失败:" + fileName + "不存在！");
            return new ResponseResult(RespEnum.FAIL,"文件不存在");
        } else {
            if (files.isFile()) {
                if (deleteFile(file)){
                    fileInfoService.deleteFile(fileInfo);
                    return new ResponseResult(RespEnum.SUCCESS,"文件删除成功");
                }else {
                    return new ResponseResult(RespEnum.FAIL,"文件删除失败");
                }
            } else {
                if (deleteDirectory(file)) {
                    return new ResponseResult(RespEnum.SUCCESS, "文件夹删除成功");
                } else {
                    return new ResponseResult(RespEnum.FAIL, "文件夹删除失败");
                }
            }
        }
    }


    private boolean deleteFile(String file) {

        File files = new File(file);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (files.exists() && files.isFile()) {
            if (files.delete()) {
                log.info("删除单个文件" + files + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + files + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + files + "不存在！");
            return false;
        }
    }


    private boolean deleteDirectory(String file) {

        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!file.endsWith(File.separator)) {
            file = file + File.separator;
            File dirFile = new File(file);
            // 如果dir对应的文件不存在，或者不是一个目录，则退出
            if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
                log.info("删除目录失败：" + file + "不存在！");
                return false;
            }
            boolean flag = true;
            // 删除文件夹中的所有文件包括子目录
            File[] files = dirFile.listFiles();
            assert files != null;
            for (File value : files) {
                // 删除子文件
                if (value.isFile()) {
                    flag = deleteFile(value.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                    // 删除子目录
                } else if (value.isDirectory()) {
                    flag = deleteDirectory(value.getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
                return false;
            }
            // 删除当前目录
            if (dirFile.delete()) {
                log.info("删除目录" + file + "成功！");
                return true;
            }
        }
        return false;
    }

}
