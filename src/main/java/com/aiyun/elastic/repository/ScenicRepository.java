package com.aiyun.elastic.repository;

import com.aiyun.elastic.domain.Scenic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhaoyuan
 */
public interface ScenicRepository extends ElasticsearchRepository<Scenic, Long> {
}
