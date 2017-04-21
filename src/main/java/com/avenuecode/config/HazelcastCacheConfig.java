package com.avenuecode.config;
/**
 *
 * @author ThirupathiReddy V
 * <br>
 * Explore more :
 *         https://blog.hazelcast.com/spring-boot/
 *
 *         More About Caching
 *
 *         https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-caching.html
 */

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@Profile("hazelcast")
public class HazelcastCacheConfig extends CachingConfigurerSupport {

    //Yet to be implemented
}
