package com.xxx.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 注册自定义的 LocalDateTime 格式化器
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(formatter2));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(formatter2));
        objectMapper.registerModule(module);

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 配置 Jackson 忽略未知的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许缺失字段，忽略时会使用默认值
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    // 对象专字符串
    public static String toStr(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }

    // 字符串转简单对象
    public static <T> T toSimpleObj(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }

    // 字符串转复杂对象
    public static <T> T toComplexObj(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }

    // 深拷贝对象，返回相同类型的新对象。注意：适配简单类型的深拷贝，不适配List,Map
    public static <T> T copyObj(T source) {
        try {
            return (T) objectMapper.convertValue(source, source.getClass());
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }

    // 深拷贝对象，返回另一个类型的新对象。注意：适配简单类型的深拷贝，不适配List,Map
    public static <T> T convertObjNoExp(Object source, Class<T> clazz) {
        try {
            return objectMapper.convertValue(source, clazz);
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }

    /**
     * 通用深拷贝方法，支持各种类型的对象深拷贝，包括基本对象、集合等
     * @param typeReference  此参数，不可去掉。否则var无法识别返回值
     */
    public static <T> T convertObjNoExp(Object source, TypeReference<T> typeReference) {
        try {
            return objectMapper.convertValue(source, typeReference);
        } catch (Exception e) {
            log.error("异常: ", e);
            return null;
        }
    }
}
