package com.xxx.base.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.commondatamodel.dao.entity.base.UserEntity;
import com.xxx.commondatamodel.dao.mapper.base.UserMapper;
import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.commondatamodel.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserSvc {

    @Autowired
    private UserMapper userMapper;

    public ApiResult<List<UserQueryPageResp>> queryPage(UserQueryPageReq req){
        List<UserEntity> userEntities = userMapper.listUser(req);
        List<UserQueryPageResp> respResult = JacksonUtil.convertObjNoExp(userEntities, new TypeReference<>() {
        });
        return ApiResult.success(respResult);
    }
}
