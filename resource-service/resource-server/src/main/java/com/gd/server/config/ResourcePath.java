package com.gd.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/29
 */
@Configuration
@ConfigurationProperties(prefix = "resource.path")
public class ResourcePath {
    public static String avatarBucket;

    public static String imageBucket;

    public static String imageUrlBase;

    public void setAvatarBucket(String avatarBucket) {
        ResourcePath.avatarBucket = avatarBucket;
    }

    public void setImageBucket(String imageBucket) {
        ResourcePath.imageBucket = imageBucket;
    }

    public void setImageUrlBase(String imageUrlBase) {
        ResourcePath.imageUrlBase = imageUrlBase;
    }

}
