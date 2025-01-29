package com.xxx.dmodel.dao.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.dmodel.dao.entity.base.UserEntity;
import com.xxx.dmodel.domain.req.base.UserQueryPageReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> listUser(@Param("req") UserQueryPageReq req);
}
