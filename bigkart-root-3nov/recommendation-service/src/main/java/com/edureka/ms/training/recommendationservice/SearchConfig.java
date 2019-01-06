package com.edureka.ms.training.recommendationservice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.SneakyThrows;

@Configuration
@EnableElasticsearchRepositories
public class SearchConfig {
	
	@Bean(name="elasticsearchTemplate")
	@SneakyThrows
	public ElasticsearchOperations elasticsearchOperations(){
		FileAttribute<?>[] attrs = {};	
		Path tmpDir = Files.createTempDirectory("temp-search-dir", attrs);
		Settings settings = Settings.builder()
				.put("http.enabled", "false")
				.put("transport.type", "local")
				.put("path.home", tmpDir)
				.build();
		
		return new ElasticsearchTemplate(new Node(settings).start().client());
				
								
	}
}
