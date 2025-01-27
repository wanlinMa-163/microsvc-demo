package com.xxx.base.svc;

import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class UserSvc {

    public ApiResult<List<UserQueryPageResp>> queryPage(UserQueryPageReq request){
        List<UserQueryPageResp> respList = new LinkedList<>();
        respList.add(new UserQueryPageResp("001","mawl"));
        respList.add(new UserQueryPageResp("002","jsh"));
        return ApiResult.success(respList);
    }
}
