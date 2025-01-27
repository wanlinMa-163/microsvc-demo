package com.xxx.order.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.commondatamodel.constant.CommonConstant;
import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.commondatamodel.grpc.GrpcReq;
import com.xxx.commondatamodel.grpc.GrpcResp;
import com.xxx.commondatamodel.grpc.UserGrpc;
import com.xxx.commondatamodel.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderSvc {

    @GrpcClient(CommonConstant.AppName.BASE)
    private UserGrpc.UserBlockingStub userBlockingStub;

    public ApiResult<List<UserQueryPageResp>> userList(UserQueryPageReq req) {
        log.info("请求入参: {}", req);
        GrpcReq grpcReq = GrpcReq.newBuilder().setParams(JacksonUtil.toStr(req)).build();
        long start = System.currentTimeMillis();
        GrpcResp grpcResp = userBlockingStub.queryPage(grpcReq);
        long end = System.currentTimeMillis();
        log.info("耗时: {}", end - start);
        ApiResult<List<UserQueryPageResp>> apiResult = JacksonUtil.toComplexObj(grpcResp.getData(), new TypeReference<>() {
        });
        return apiResult;
    }
}
