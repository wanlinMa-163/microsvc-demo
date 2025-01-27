package com.xxx.commondatamodel.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PkIdUtil {

    public String getId(){
        return UUID.randomUUID().toString();
    }
}
