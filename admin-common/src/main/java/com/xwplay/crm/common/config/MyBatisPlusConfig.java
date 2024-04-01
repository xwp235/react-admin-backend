package com.xwplay.crm.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.xwplay.crm.common.handler.MenuNameTypeHandler;
import com.xwplay.crm.common.handler.TimestamptzTypeHandler;
import com.xwplay.crm.common.handler.types.MenuName;
import com.xwplay.crm.common.handler.types.TstzRange;
import com.xwplay.crm.common.handler.TstzRangeTypeHandler2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;


@Configuration
public class MyBatisPlusConfig {

    @Bean
    MybatisPlusInterceptor mybatisPlusInterceptor() {
        var interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

    @Bean
    PostgreKeyGenerator postgreKeyGenerator() {
      return new PostgreKeyGenerator();
    }

    @Bean
    ConfigurationCustomizer configurationCustomizer(ObjectMapper objectMapper) {
        return configuration -> {
            var typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//            typeHandlerRegistry.register(Pair.class,new TstzRangeTypeHandler());
            typeHandlerRegistry.register(TstzRange.class,new TstzRangeTypeHandler2());
            typeHandlerRegistry.register(ZonedDateTime.class, new TimestamptzTypeHandler());
            typeHandlerRegistry.register(MenuName.class,new MenuNameTypeHandler(objectMapper));
        };
    }

}
