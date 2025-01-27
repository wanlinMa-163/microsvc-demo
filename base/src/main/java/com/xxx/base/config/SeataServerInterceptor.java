package com.xxx.base.config;

import io.grpc.*;
import io.seata.core.context.RootContext;

public class SeataServerInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        String xid = headers.get(Metadata.Key.of("XID", Metadata.ASCII_STRING_MARSHALLER));
        if (xid != null) {
            RootContext.bind(xid);
        }
        return next.startCall(call, headers);
    }
}