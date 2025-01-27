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
@RequestMapping("/history")
@Slf4j
public class HistoryUserController {

    @Autowired
    private OrderSvc orderSvc;

    @GetMapping("/userList")
    @ResponseBody
    public ApiResult<List<UserQueryPageResp>> userList(@RequestBody UserQueryPageReq req) {
        return orderSvc.userList(req);
    }

}
