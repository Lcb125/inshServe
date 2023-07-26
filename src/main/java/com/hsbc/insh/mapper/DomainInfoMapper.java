package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DomainInfoMapper {

    boolean insertDomainInfo(UserInfo userInfo);

    boolean updateDomainInfo(UserInfo userInfo);

    boolean deleteDomainInfo(UserInfo userInfo);

    List<ApiInfo> queryDomains(UserInfo userInfo);
}
