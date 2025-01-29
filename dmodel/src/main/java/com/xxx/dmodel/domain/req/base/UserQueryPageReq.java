package com.xxx.dmodel.domain.req.base;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserQueryPageReq {

    private String name;
}
