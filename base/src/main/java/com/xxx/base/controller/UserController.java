package com.xxx.base.controller;

import com.xxx.base.svc.UserSvc;
import com.xxx.datamodel.domain.dto.ApiResult;
import com.xxx.datamodel.domain.req.base.UserQueryPageReq;
import com.xxx.datamodel.domain.req.base.UserUpdateConsumptionLevelReq;
import com.xxx.datamodel.domain.resp.base.UserQueryPageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("updateConsumptionLevel")
    public ApiResult<?> updateConsumptionLevel(@RequestBody UserUpdateConsumptionLevelReq req){
        return userSvc.updateConsumptionLevel(req);
    }

}
