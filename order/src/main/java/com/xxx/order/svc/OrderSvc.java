package com.xxx.order.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.base.dto.ApiResult;
import com.xxx.base.dto.UserQueryPageReq;
import com.xxx.base.dto.UserQueryPageResp;
import com.xxx.base.grpc.GrpcReq;
import com.xxx.base.grpc.GrpcResp;
import com.xxx.base.grpc.UserGrpc;
import com.xxx.base.util.JacksonUtil;
import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderSvc {

    @GrpcClient("base-grpc-channel")
    private Channel baseGrpcChannel;

    public ApiResult<List<UserQueryPageResp>> queryPage(UserQueryPageReq req) {
        log.info("请求入参: {}", req);
        UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(baseGrpcChannel);
        GrpcReq grpcReq = GrpcReq.newBuilder().setParams(JacksonUtil.toStr(req)).build();
        long start = System.currentTimeMillis();
        GrpcResp grpcResp = userBlockingStub.queryPage(grpcReq);
        long end = System.currentTimeMillis();
        log.info("耗时: {}", end - start);
        ApiResult<List<UserQueryPageResp>> apiResult = JacksonUtil.toComplexObj(grpcResp.getData(), new TypeReference<>() {
        });
        log.info("请求出参: {}", apiResult);
        return apiResult;
    }
}
