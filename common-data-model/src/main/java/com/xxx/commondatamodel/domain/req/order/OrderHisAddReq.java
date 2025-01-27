package com.xxx.commondatamodel.domain.req.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderHisAddReq {

    private String userId;

    private String orderId;
    private String orderName;

}
