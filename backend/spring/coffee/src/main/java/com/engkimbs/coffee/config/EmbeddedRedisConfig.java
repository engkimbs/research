package com.engkimbs.coffee.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Profile("!prod")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int port;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = RedisServer.builder()
                .port(port)
                .setting("maxmemory 512M")
                .build();
        try {
            redisServer.start();
        } catch (Exception e) {
            log.warn("if another redis is working. just going on");
        }
    }

    @PreDestroy
    public void stopRedis() {
        if(redisServer != null)
            redisServer.stop();
    }
}
