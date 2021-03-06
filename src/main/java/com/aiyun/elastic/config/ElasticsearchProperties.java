package com.aiyun.elastic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhaoyuan
 */
@ConfigurationProperties(
        prefix = "spring.data.elasticsearch"
)
public class ElasticsearchProperties {

    private String clusterName = "elasticsearch";
    private String clusterNodes;
    private Map<String, String> properties = new HashMap();

    public ElasticsearchProperties() {
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterNodes() {
        return this.clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
