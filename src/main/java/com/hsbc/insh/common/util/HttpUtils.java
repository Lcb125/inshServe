package com.hsbc.insh.common.util;

import cn.hutool.json.JSONUtil;
import com.hsbc.insh.common.entity.AddDBReq;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpUtils {

    @Value("${serve.python.addDBDocs}")
    String addDBUrl;

    @Value("${serve.python.removeDBDocs}")
    String deleteDBUrl;

    @Async
    public void addDBFile(List<AddDBReq> list) {

        log.info("addDBUrl==="+addDBUrl);
        log.info("req for python ==="+JSONUtil.toJsonStr(list));
        try {
            String body = HttpRequest.post(addDBUrl)
                    .header("Content-Type", "application/json")
                    .timeout(360000)
                    .body(JSONUtil.toJsonStr(list))
                    .execute()
                    .body();
            log.info("调用python服务resp======="+body);
            log.info("增加文件入向量库成功");
        } catch (HttpException | ConvertException e) {
            log.info("调用python服务增加出现了异常："+e.getMessage());
        }

    }

    public void deleteDBFile(String product,String fileName) {

        log.info("deleteDBUrl==="+deleteDBUrl);
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> reqMap = new HashMap<>();
        reqMap.put("product",product);
        reqMap.put("filename",fileName);
        list.add(reqMap);
        log.info("list==="+JSONUtil.toJsonStr(list));
        try {
            String body = HttpRequest.post(deleteDBUrl)
                    .header("Content-Type", "application/json")
                    .timeout(360000)
                    .body(JSONUtil.toJsonStr(list))
                    .execute()
                    .body();
            log.info("调用python服务resp======="+body);
            log.info("删除文件入向量库成功");
        } catch (HttpException | ConvertException e) {
            log.info("调用python服务删除出现了异常："+e.getMessage());
        }

    }

}
