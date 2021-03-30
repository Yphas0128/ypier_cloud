package cn.ypier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



/**
 * @Author Ypier
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "cn.ypier.mapper")
@EnableFeignClients
public class SqlServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqlServerApplication.class,args);
    }
}
