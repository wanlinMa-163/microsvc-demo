package com.xxx.order.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.commondatamodel.constant.CommonConstant;
import com.xxx.commondatamodel.dao.entity.order.OrderHisEntity;
import com.xxx.commondatamodel.dao.mapper.order.OrderHisMapper;
import com.xxx.commondatamodel.domain.dto.ApiResult;
import com.xxx.commondatamodel.domain.req.base.UserQueryPageReq;
import com.xxx.commondatamodel.domain.req.base.UserUpdateConsumptionLevelReq;
import com.xxx.commondatamodel.domain.req.order.OrderHisAddReq;
import com.xxx.commondatamodel.domain.resp.base.UserQueryPageResp;
import com.xxx.commondatamodel.grpc.GrpcReq;
import com.xxx.commondatamodel.grpc.GrpcResp;
import com.xxx.commondatamodel.grpc.UserGrpc;
import com.xxx.commondatamodel.util.JacksonUtil;
import com.xxx.commondatamodel.util.PkIdUtil;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderHistorySvc {

    @Autowired
    private PkIdUtil pkIdUtil;
    @Autowired
    private OrderHisMapper orderHisMapper;

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


    @GlobalTransactional
    public ApiResult<?> add(OrderHisAddReq req) {

        // 成功调用自己模块
        OrderHisEntity orderHisAdd = new OrderHisEntity();
        orderHisAdd.setId(pkIdUtil.getId());
        orderHisAdd.setCreateTime(LocalDateTime.now());
        orderHisMapper.insert(orderHisAdd);

        // 再成功调用 base 模块
        UserUpdateConsumptionLevelReq req2 = new UserUpdateConsumptionLevelReq();
        req2.setUserId(req.getUserId());
        GrpcResp grpcResp = userBlockingStub.updateConsumptionLevel(GrpcReq.newBuilder().setParams(JacksonUtil.toStr(req2)).build());
        ApiResult<?> apiResult = JacksonUtil.toComplexObj(grpcResp.getData(), new TypeReference<>() {
        });

        // 抛出异常
        throw new RuntimeException("异常");
        // return apiResult;
    }
}
