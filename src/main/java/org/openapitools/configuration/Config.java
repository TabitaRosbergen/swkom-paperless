package org.openapitools.configuration;

/*
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig
{
    public static final String DOCUMENTS_INDEX_NAME = "documents";

    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private int port;

    @Bean
    public RestClient getRestClient() {
        return RestClient.builder(
                new HttpHost(host, port)).build();
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        JacksonJsonpMapper mapper = new JacksonJsonpMapper();
        mapper.objectMapper().registerModule(new JavaTimeModule());
        mapper.objectMapper().registerModule(new JsonNullableModule());
        return new RestClientTransport(
                getRestClient(), mapper);
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient(){
        return new ElasticsearchClient(getElasticsearchTransport());
    }

}


 */

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org.openapitools.services.impl")
public class Config extends AbstractElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }
}