package com.xxx.commondatamodel.dao.entity.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class UserEntity {
    private String id;
    private String name;
}
