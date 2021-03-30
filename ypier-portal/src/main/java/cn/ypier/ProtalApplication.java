package cn.ypier;/*
 * @Author Ypier
 */

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("cn.ypier.mapper")
@EnableAutoDataSourceProxy
public class ProtalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtalApplication.class,args);
    }
}
