package com.xxx.base.svc.grpc;

import com.xxx.base.svc.UserSvc;
import com.xxx.common.util.JacksonUtil;
import com.xxx.datamodel.domain.dto.ApiResult;
import com.xxx.datamodel.domain.req.base.UserQueryPageReq;
import com.xxx.datamodel.domain.req.base.UserUpdateConsumptionLevelReq;
import com.xxx.datamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.datamodel.grpc.GrpcReq;
import com.xxx.datamodel.grpc.GrpcResp;
import com.xxx.datamodel.grpc.UserGrpc;
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
