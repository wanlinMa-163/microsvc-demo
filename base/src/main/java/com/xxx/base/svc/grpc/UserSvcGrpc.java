package com.xxx.base.svc.grpc;

import com.xxx.base.dto.ApiResult;
import com.xxx.base.dto.UserQueryPageReq;
import com.xxx.base.dto.UserQueryPageResp;
import com.xxx.base.grpc.GrpcReq;
import com.xxx.base.grpc.GrpcResp;
import com.xxx.base.grpc.UserGrpc;
import com.xxx.base.svc.UserSvc;
import com.xxx.base.util.JacksonUtil;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
@Slf4j
public class UserSvcGrpc extends UserGrpc.UserImplBase {

    @Autowired
    private UserSvc userSvc;
    @Autowired
    private JacksonUtil jacksonUtil;

    @Override
    public void queryPage(GrpcReq request, StreamObserver<GrpcResp> responseObserver) {
        UserQueryPageReq pageReq = JacksonUtil.toSimpleObj(request.getParams(), UserQueryPageReq.class);

        long start = System.currentTimeMillis();
        ApiResult<List<UserQueryPageResp>> resp = userSvc.queryPage(pageReq);
        long end = System.currentTimeMillis();
        log.info("userSvc耗时: {}", end - start);

        GrpcResp grpcResp = GrpcResp.newBuilder().setData(JacksonUtil.toStr(resp)).build();
        responseObserver.onNext(grpcResp);
        responseObserver.onCompleted();
    }
}
