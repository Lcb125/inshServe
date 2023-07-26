package com.hsbc.insh.mapper;


import com.hsbc.insh.common.entity.ApiInfo;
import com.hsbc.insh.common.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeBaseMapper {

    boolean insertKnowledgeBase(UserInfo userInfo);

    boolean updateKnowledgeBase(UserInfo userInfo);

    boolean deleteKnowledgeBase(UserInfo userInfo);

    List<ApiInfo> queryKnowledgeBases(UserInfo userInfo);
}
