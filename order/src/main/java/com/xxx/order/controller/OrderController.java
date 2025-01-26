package com.xxx.order.controller;

import com.xxx.grpcinter.grpc.GreeterGrpc;
import com.xxx.grpcinter.grpc.HelloRequest;
import com.xxx.grpcinter.grpc.HelloResponse;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GrpcClient("greeter-service")
    private Channel channel;

    @RequestMapping("/test")
    public String test() {
        return "order";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        String name = "mawl";
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = stub.sayHello(request);
        return response.getMessage();
    }

}
