package cn.ypier.config;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author Ypier
 */
//@Configuration
public class SeataAutoConfig {

//    @Value("${spring.application.name}")
    private  String applicationId;

//    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return  new GlobalTransactionScanner(applicationId,"my_test_tx_group");
    }
}
