package com.aiyun.elastic.config;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author zhaoyuan
 */
@Configuration
@AutoConfigureAfter(TransportClientConfiguration.class)
@EnableElasticsearchRepositories(basePackages = "com.aiyun.elastic.repository")
public class ElasticSearchTemplateConfiguration {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(TransportClient transportClient) {
        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(transportClient);
        return elasticsearchTemplate;
    }
}
