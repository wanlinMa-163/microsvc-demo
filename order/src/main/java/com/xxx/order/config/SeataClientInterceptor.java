package com.xxx.order.config;

import io.grpc.*;
import io.seata.core.context.RootContext;

public class SeataClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                String xid = RootContext.getXID();
                if (xid != null) {
                    headers.put(Metadata.Key.of("XID", Metadata.ASCII_STRING_MARSHALLER), xid);
                }
                super.start(responseListener, headers);
            }
        };
    }
}