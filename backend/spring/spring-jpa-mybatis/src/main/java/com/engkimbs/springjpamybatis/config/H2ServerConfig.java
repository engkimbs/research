package com.engkimbs.springjpamybatis.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("local")
@Configuration
public class H2ServerConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource h2TcpServer() throws Exception {
        Server server = Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort",
                "9092").start();
        return new HikariDataSource();
    }
}
