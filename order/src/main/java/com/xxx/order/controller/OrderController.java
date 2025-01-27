package com.xxx.order.controller;

import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.order.svc.OrderSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderSvc orderSvc;

    @RequestMapping("/test")
    public String test() {
        return "order";
    }

    @GetMapping("/grpcTestA")
    @ResponseBody
    public ApiResult<List<UserQueryPageResp>> queryPage(@RequestBody UserQueryPageReq req) {
        return orderSvc.queryPage(req);
    }

    @GetMapping("/constantTestA")
    @ResponseBody
    public ApiResult<?> constantTestA() {
        return orderSvc.constantTestA();
    }


}
