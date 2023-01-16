package com.ygh.ihrm.company;

import com.ygh.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * @author YangGh
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.ygh.ihrm")
@EntityScan(value = "com.ygh.ihrm.domain.company")
public class CompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
