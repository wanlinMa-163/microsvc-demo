package com.xxx.commondatamodel.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "order_his")
public class OrderHisEntity {
    private String id;
    private LocalDateTime createTime;
}
