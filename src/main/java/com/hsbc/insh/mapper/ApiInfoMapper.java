package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiInfoMapper {

    boolean insertApiInfo(UserInfo userInfo);

    boolean updateApiInfo(UserInfo userInfo);

    boolean deleteApiInfo(UserInfo userInfo);

    List<ApiInfo> queryApis(UserInfo userInfo);
}
