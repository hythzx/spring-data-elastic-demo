package com.aiyun.elastic;

import com.aiyun.elastic.config.ElasticsearchProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(
        ElasticsearchProperties.class
)
public class SpringDataElasticDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataElasticDemoApplication.class, args);
    }
}
