package com.xxx.dmodel.dao.entity.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class UserEntity {
    private String id;
    private String name;

    private Integer consumptionLevel; // 消费等级
}
