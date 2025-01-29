package com.xxx.datamodel.domain.req.base;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserCreateReq {

    private String userId;

    private String name;
}
