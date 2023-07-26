package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    boolean insertUserInfo(UserInfo userInfo);

    List<UserInfo> queryUserInfo(UserInfo userInfo);

    boolean updateUserInfo(UserInfo userInfo);

    boolean deleteUserInfo(UserInfo userInfo);

    List<UserInfo> queryUsers(UserInfo userInfo);
}
