package com.xxx.base.controller;

import com.xxx.base.svc.UserSvc;
import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserSvc userSvc;

    @GetMapping("queryPage")
    public ApiResult<List<UserQueryPageResp>> queryPage(@RequestBody UserQueryPageReq req) {
        return userSvc.queryPage(req);
    }

}
