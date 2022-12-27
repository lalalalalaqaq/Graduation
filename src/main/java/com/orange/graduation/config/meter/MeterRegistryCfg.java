package com.orange.graduation.config.meter;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author orange.zhang
 * @date 2022/12/25 14:59
 */

@Component
public class MeterRegistryCfg {

//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> configurer(
//            @Value("${spring.application.name}") String applicationName) {
//        return (registry) -> registry.config().commonTags("application", applicationName);
//    }
}
