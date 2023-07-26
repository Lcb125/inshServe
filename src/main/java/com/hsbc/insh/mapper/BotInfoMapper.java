package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BotInfoMapper {

    boolean insertBotInfo(UserInfo userInfo);

    boolean updateBotInfo(UserInfo userInfo);

    boolean deleteBotInfo(UserInfo userInfo);

    List<ApiInfo> queryBots(UserInfo userInfo);
}
