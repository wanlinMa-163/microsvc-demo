package com.xxx.datamodel.constant;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class CommonConstantLoader {

    @PostConstruct
    private void init() {

        // 假设这里从数据库查询出来的
        HashMap<String, String> userTypeMap = new HashMap<>();
        userTypeMap.put("typeA", "类型A");
        userTypeMap.put("typeB", "类型B");
        CommonConstant.UserType.USER_TYPE_NAME_MAP = userTypeMap;
        CommonConstant.UserType.USER_TYPE_LIST = List.of("typeA","typeB");

    }
}
