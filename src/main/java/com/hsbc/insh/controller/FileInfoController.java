package com.hsbc.insh.controller;


import com.hsbc.insh.common.Enum.RespEnum;
import com.hsbc.insh.common.entity.FileInfo;
import com.hsbc.insh.common.entity.ResponseResult;
import com.hsbc.insh.common.page.PageRequest;
import com.hsbc.insh.common.page.PageResponse;
import com.hsbc.insh.common.util.FileUtils;
import com.hsbc.insh.common.util.LanguageUtils;
import com.hsbc.insh.mapper.FileInfoMapper;
import com.hsbc.insh.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileInfoController {

    private final static String PATH = "C:/codebase/insh-gpt/pdf/";

    private final static String SEPERATOR = File.separator;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    FileInfoMapper fileInfoMapper;

    @Autowired
    FileUtils fileUtils;

    @Autowired
    LanguageUtils languageUtils;

    @PostMapping(value = "/insertFile")
    @ResponseBody
    public ResponseResult insertFile(@RequestBody FileInfo fileInfo)  {

        log.info("fileInfo="+fileInfo);
        List<FileInfo> userInfos = fileInfoService.queryFile(fileInfo);
        if (userInfos.size()>0){
            return new ResponseResult(RespEnum.EXIST,"文件已存在");
        }

        fileInfo.setCreateTime(new Date());
//        fileInfo.setCreateBy("Y");

        boolean result = fileInfoService.insertFile(fileInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }
    }


    @PostMapping(value = "/queryFiles")
    @ResponseBody
    public ResponseResult queryUser(@RequestBody PageRequest pageRequest)  {

        PageResponse pageInfo = fileInfoService.findPage(pageRequest);
        if (pageInfo.getListInfo().size()>0){
            return new ResponseResult(RespEnum.SUCCESS,pageInfo);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到数据");
    }


    @PostMapping(value = "/deleteFile")
    @ResponseBody
    public ResponseResult deleteFile(@RequestBody FileInfo fileInfo)  {

        boolean result = fileInfoService.deleteFile(fileInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息删除成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息删除失败");
        }
    }

    @GetMapping(value = "/viewFiles")
    @ResponseBody
    public void viewFiles(@RequestParam(value = "fileType") String fileType, @RequestParam(value = "fileName") String fileName, HttpServletResponse response)  {

        log.info("filetype="+fileType+",fileName="+fileName);
        String filePath = PATH+fileType+SEPERATOR+fileName;
        fileUtils.viewFiles(filePath,response);

    }



    @PostMapping(value = "/addFiles")
    @ResponseBody
    public ResponseResult addFile(HttpServletRequest request,String fileType,String product)  {

        return fileUtils.uploadFiles(request,fileType);

    }
    @PostMapping(value = "/deleteFiles")
    @ResponseBody
    public ResponseResult deleteFiles(@RequestBody FileInfo fileInfo)  {

        return fileUtils.deleteFiles(fileInfo);

    }
    @GetMapping(value = "/getProducts")
    @ResponseBody
    public ResponseResult getProducts(@RequestParam(value = "fileType",required = false) String fileType)  {

        List<Map<String,String>> resultMap = new ArrayList<>();
        List<String> products = fileInfoMapper.getProducts(fileType);
        for (String product:products){
            Map<String,String> map = new HashMap<>();
            map.put(product,languageUtils.getPinyin(product,""));
            resultMap.add(map);
        }
        return new ResponseResult(RespEnum.SUCCESS,resultMap);

    }

}
