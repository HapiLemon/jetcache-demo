package com.ftl.demo.configuration;

import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.ftl.demo.serialize.JsonSerialPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class JetCacheConfig {

    /**
     * 使用方法
     *
     * @return
     * @Cached( name = "user:", key = "#userId", serialPolicy = "bean:jsonPolicy")
     */
    @Bean(name = "jsonPolicy")
    public JsonSerialPolicy JsonSerialPolicy() {
        return new JsonSerialPolicy();
    }

    /**
     * 手动创建一个springConfigProvider Bean
     * 达到自定义序列化工具的目的
     *
     * @return
     */
    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
                // 当yml配置文件中指定json 使用这个序列化工具
                if (valueEncoder.equalsIgnoreCase("json")) {
                    return new JsonSerialPolicy().encoder();
                }
                return super.parseValueEncoder(valueEncoder);

            }

            @Override
            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
                // 当yml配置文件中指定json 使用这个序列化工具
                if (valueDecoder.equalsIgnoreCase("json")) {
                    return new JsonSerialPolicy().decoder();
                }
                return super.parseValueDecoder(valueDecoder);
            }
        };
    }

}
