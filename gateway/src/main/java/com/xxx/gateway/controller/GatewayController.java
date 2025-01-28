package com.xxx.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
@Slf4j
@RefreshScope
public class GatewayController {

    @GetMapping("/test")
    @ResponseBody
    public String add() {
        return "gateway";
    }
}
