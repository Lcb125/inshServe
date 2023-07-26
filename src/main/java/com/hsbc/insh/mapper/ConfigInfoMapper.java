package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConfigInfoMapper {

    boolean insertConfigInfo(UserInfo userInfo);

    boolean updateConfigInfo(UserInfo userInfo);

    boolean deleteConfigInfo(UserInfo userInfo);

    List<ApiInfo> queryConfigs(UserInfo userInfo);
}
