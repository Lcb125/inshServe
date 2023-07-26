package com.hsbc.insh.controller;


import com.hsbc.insh.common.Enum.RespEnum;
import com.hsbc.insh.common.entity.ResponseResult;
import com.hsbc.insh.common.entity.UserInfo;
import com.hsbc.insh.common.page.PageRequest;
import com.hsbc.insh.common.page.PageResponse;
import com.hsbc.insh.common.util.TokenUtils;
import com.hsbc.insh.mapper.UserInfoMapper;
import com.hsbc.insh.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final static String ADMIN = "admin";

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserInfoMapper userInfoMapper;


    @PostMapping(value = "/insertUser")
    @ResponseBody
    public ResponseResult insertUser(@RequestBody UserInfo userInfo)  {
        UserInfo user = new UserInfo();
        user.setUserName(userInfo.getUserName());
        user.setStatus("Y");
        List<UserInfo> userInfos = userInfoMapper.queryUsers(user);
        if (userInfos.size()>0){
            return new ResponseResult(RespEnum.EXIST,"当前用户已存在");
        }

        userInfo.setStatus("Y");
        boolean result = userInfoService.insertUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseResult queryUser(@RequestBody UserInfo userInfo)  {
        log.info("userinfo"+userInfo);

        List<UserInfo> resultUser = userInfoService.queryUserInfo(userInfo);
        if (resultUser.size()>0){
            String token = tokenUtils.getToken(userInfo.getUserName(), userInfo.getPassword());
//            boolean equals = StringUtils.equals(ADMIN, resultUser.getUserType());
            return new ResponseResult(RespEnum.SUCCESS,token);
        }
        return new ResponseResult(RespEnum.FAIL,"用户名或密码错误");
    }

    @PostMapping(value = "/updateUser")
    @ResponseBody
    public ResponseResult updateUser(@RequestBody UserInfo userInfo)  {

        userInfo.setUpdateTime(new Date());
        boolean result = userInfoService.updateUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息插入成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息插入失败");
        }
    }

    @PostMapping(value = "/deleteUser")
    @ResponseBody
    public ResponseResult deleteUser(@RequestBody UserInfo userInfo)  {
//        String token = request.getHeader("authToken");
//        String name = tokenUtils.verify(token);
//        if (!"admin".equals(name)){
//            return new ResponseResult(RespEnum.NOAUTH,"无管理员权限");
//        }

        userInfo.setStatus("N");
        boolean result = userInfoService.deleteUserInfo(userInfo);

        if (result){
            return new ResponseResult(RespEnum.SUCCESS,"信息删除成功");
        }else {
            return new ResponseResult(RespEnum.FAIL,"信息删除失败");
        }
    }


    @PostMapping(value = "/queryUsers")
    @ResponseBody
    public ResponseResult queryUsers(@RequestBody PageRequest pageRequest, HttpServletRequest request)  {

        String token = request.getHeader("authToken");
        String result = tokenUtils.verify(token);
        String[] split = result.split("-");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(split[0]);
        userInfo.setPassword(split[1]);
        PageResponse pageResponse = userInfoService.queryUsersInfo(pageRequest);
        List<UserInfo> userInfos = userInfoService.queryUserInfo(userInfo);
        if (userInfos.size()>0){
            UserInfo user = userInfos.get(0);
            pageResponse.setUserType(user.getUserLevel());
        }


        if (pageResponse.getListInfo().size()>0){

            return new ResponseResult(RespEnum.SUCCESS,pageResponse);
        }
        return new ResponseResult(RespEnum.FAIL,"未查询到用户信息");
    }


}
