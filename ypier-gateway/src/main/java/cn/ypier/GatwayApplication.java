package cn.ypier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Ypier
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatwayApplication.class,args);
    }
}
