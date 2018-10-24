package com.aiyun.elastic.config;


import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyuan
 */
@Configuration
public class TransportClientConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    private TransportClient client = null;

    private final Logger logger = LoggerFactory.getLogger(TransportClientConfiguration.class);

    public TransportClientConfiguration(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                /**
                 * 客户端去嗅探整个集群的状态，注意，如果客户端和Elastic集群在同一局域网内，可以将该属性设置成true以提高查询性能，否则需要设置成false
                 */
                .put("client.transport.sniff", elasticsearchProperties.getProperties().getOrDefault("client.transport.sniff", "false"))
                .put("client.transport.ignore_cluster_name", elasticsearchProperties.getProperties().getOrDefault("client.transport.ignore_cluster_name", "false"))
                .put("client.transport.ping_timeout", elasticsearchProperties.getProperties().getOrDefault("client.transport.ping_timeout", "5s"))
                /**
                 * ElasticSearch的集群名称，必须设置
                 **/
                .put("cluster.name", elasticsearchProperties.getClusterName()).build();
        String clusterNodes = elasticsearchProperties.getClusterNodes();
        List<TransportNode> transportNodeList = new ArrayList<>();
        if (StringUtils.isEmpty(clusterNodes)) {
            transportNodeList.add(new TransportNode("127.0.0.1", 9300));
        }else {
            String[] nodes = clusterNodes.split(",");
            for (String node : nodes) {
                transportNodeList.add(new TransportNode(node));
            }
        }
        client = new PreBuiltTransportClient(settings);
        for (TransportNode transportNode : transportNodeList) {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(transportNode.getHost()), transportNode.getPort()));
        }
        return client;
    }



    @PreDestroy
    public void destroy() {
        logger.info("Closing Elasticsearch Client");
        if (client != null) {
            client.close();
        }
    }

    static class TransportNode {
        private String host;
        private int port;

        private final Logger logger = LoggerFactory.getLogger(TransportNode.class);

        public TransportNode() {
        }


        public TransportNode(String node) {
            String[] split = node.split(":");
            if (split.length == 2) {
                this.host = split[0];
                String port = split[1];
                if (StringUtils.isNumeric(port)) {
                    this.port = Integer.valueOf(port);
                }else {
                    logger.warn("ElasticSearch node配置错误，配置项是: {},错误信息：端口号必须是int类型!");
                }
            }
        }

        public TransportNode(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
