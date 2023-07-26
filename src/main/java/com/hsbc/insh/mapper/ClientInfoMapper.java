package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientInfoMapper {

    boolean insertClientInfo(UserInfo userInfo);

    boolean updateClientInfo(UserInfo userInfo);

    boolean deleteClientInfo(UserInfo userInfo);

    List<ApiInfo> queryClients(UserInfo userInfo);
}
