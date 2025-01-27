package com.xxx.commondatamodel.dao.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.commondatamodel.dao.entity.base.UserEntity;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> listUser(@Param("req") UserQueryPageReq req);
}
