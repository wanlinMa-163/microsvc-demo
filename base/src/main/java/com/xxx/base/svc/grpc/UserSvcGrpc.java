package com.xxx.base.svc.grpc;

import com.xxx.base.svc.UserSvc;
import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.req.base.UserUpdateConsumptionLevelReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.commondatamodel.grpc.GrpcReq;
import com.xxx.commondatamodel.grpc.GrpcResp;
import com.xxx.commondatamodel.grpc.UserGrpc;
import com.xxx.commondatamodel.util.JacksonUtil;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@GrpcService
public class UserSvcGrpc extends UserGrpc.UserImplBase {

    @Autowired
    private UserSvc userSvc;
    @Autowired
    private JacksonUtil jacksonUtil;

    @Override
    public void queryPage(GrpcReq request, StreamObserver<GrpcResp> responseObserver) {
        UserQueryPageReq pageReq = JacksonUtil.toSimpleObj(request.getParams(), UserQueryPageReq.class);
        ApiResult<List<UserQueryPageResp>> resp = userSvc.queryPage(pageReq);
        GrpcResp grpcResp = GrpcResp.newBuilder().setData(JacksonUtil.toStr(resp)).build();
        responseObserver.onNext(grpcResp);
        responseObserver.onCompleted();
    }

    @Override
    public void updateConsumptionLevel(GrpcReq request, StreamObserver<GrpcResp> responseObserver) {
        UserUpdateConsumptionLevelReq req = JacksonUtil.toSimpleObj(request.getParams(), UserUpdateConsumptionLevelReq.class);
        ApiResult resp = userSvc.updateConsumptionLevel(req);
        GrpcResp grpcResp = GrpcResp.newBuilder().setData(JacksonUtil.toStr(resp)).build();
        responseObserver.onNext(grpcResp);
        responseObserver.onCompleted();
    }
}
