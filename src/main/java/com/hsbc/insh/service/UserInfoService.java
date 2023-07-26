package com.hsbc.insh.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsbc.insh.common.entity.UserInfo;
import com.hsbc.insh.common.page.PageRequest;
import com.hsbc.insh.common.page.PageResponse;
import com.hsbc.insh.common.util.PageUtils;
import com.hsbc.insh.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public boolean insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public List<UserInfo> queryUserInfo(UserInfo userInfo) {
        return userInfoMapper.queryUserInfo(userInfo);
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    public boolean deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteUserInfo(userInfo);
    }

    public PageResponse queryUsersInfo(PageRequest pageRequest) {

        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<UserInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus("Y");
        List<UserInfo> sysMenus = userInfoMapper.queryUsers(userInfo);
        return new PageInfo<UserInfo>(sysMenus);
    }
}
