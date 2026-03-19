package com.example.sorl.config;

import org.apache.solr.client.solrj.SolrClient;

import org.apache.solr.client.solrj.jetty.HttpJettySolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class SolrConfig {
    @Value("${spring.data.solr.host}")
    private String solrUrl;

    @Bean
    public SolrClient solrClient(){
        return new HttpJettySolrClient.Builder(solrUrl)
                .withDefaultCollection("produtos")
                .withConnectionTimeout(6000, TimeUnit.MILLISECONDS)
                .build();
    }
}
