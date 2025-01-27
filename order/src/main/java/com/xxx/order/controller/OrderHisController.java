package com.xxx.order.controller;

import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.order.OrderHisAddReq;
import com.xxx.order.svc.OrderHistorySvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderHis")
@Slf4j
public class OrderHisController {

    @Autowired
    private OrderHistorySvc orderHistorySvc;

    // 每新增一笔订单，就创建一条历史记录，同时更新用户的消费积分
    @PostMapping("/add")
    @ResponseBody
    public ApiResult<?> add(@RequestBody OrderHisAddReq req) {
        return orderHistorySvc.add(req);
    }

}
