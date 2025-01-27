package com.xxx.commondatamodel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 不启用数据库：@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
 * 不启用mybatis plus: mybatis-plus.enable=false
 */
@Configuration
@ConditionalOnProperty(name = "mybatis-plus.enable", havingValue = "true", matchIfMissing = true)
@MapperScan("com.xxx.commondatamodel.dao.mapper")
public class MybatisPlusAutoConfig {

}
