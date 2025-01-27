package com.xxx.order.controller;

import com.xxx.base.dto.ApiResult;
import com.xxx.base.dto.UserQueryPageReq;
import com.xxx.base.dto.UserQueryPageResp;
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

    @GetMapping("/hello")
    @ResponseBody
    public ApiResult<List<UserQueryPageResp>> queryPage(@RequestBody UserQueryPageReq req) {
        return orderSvc.queryPage(req);
    }

}
