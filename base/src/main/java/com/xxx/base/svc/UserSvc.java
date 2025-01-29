package com.xxx.base.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.common.util.JacksonUtil;
import com.xxx.datamodel.dao.entity.base.UserEntity;
import com.xxx.datamodel.dao.mapper.base.UserMapper;
import com.xxx.datamodel.domain.dto.ApiResult;
import com.xxx.datamodel.domain.req.base.UserQueryPageReq;
import com.xxx.datamodel.domain.req.base.UserUpdateConsumptionLevelReq;
import com.xxx.datamodel.domain.resp.base.UserQueryPageResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserSvc {

    @Autowired
    private UserMapper userMapper;

    public ApiResult<List<UserQueryPageResp>> queryPage(UserQueryPageReq req) {
        List<UserEntity> userEntities = userMapper.listUser(req);
        List<UserQueryPageResp> respResult = JacksonUtil.convertObjNoExp(userEntities, new TypeReference<>() {
        });
        return ApiResult.success(respResult);
    }

    // @GlobalTransactional // 只要包含在 GlobalTransactional 被调用的方法里，就可以正常提交进行全局事务管理，所以@GlobalTransactional 可以不用写
    // @Transactional // 只要包含在 GlobalTransactional 被调用的方法里，写 @Transactional 也不会被影响，也会被全局事务管理
    public ApiResult<?> updateConsumptionLevel(UserUpdateConsumptionLevelReq req) {
        String userId = req.getUserId();
        Integer consumptionLevel = userMapper.selectById(userId).getConsumptionLevel();
        UserEntity userUpdate = new UserEntity();
        userUpdate.setId(userId);
        userUpdate.setConsumptionLevel(++consumptionLevel);
        userMapper.updateById(userUpdate);
        return ApiResult.success(null);
    }
}
