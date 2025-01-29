package com.xxx.datamodel.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "set")
@NoArgsConstructor
public class ApiResult<T> {
    private int code;
    private String message;
    private T data;

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态工厂方法
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(200, "Success", data);
    }

    public static ApiResult<?> error(int code, String message) {
        return new ApiResult<>(code, message, null);
    }
}
