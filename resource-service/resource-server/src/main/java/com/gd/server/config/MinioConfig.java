package com.gd.server.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    private String url;
    private String username;
    private String password;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(url).credentials(username, password).build();
    }

}
