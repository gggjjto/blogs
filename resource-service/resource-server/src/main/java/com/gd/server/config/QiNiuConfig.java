package com.gd.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuConfig {
    private String accessKey;
    private String secretKey;
    private String prefixUrl;
}
