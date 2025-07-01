package com.example.mcpserver;

import com.example.mcpserver.service.PixService;
import org.springframework.ai.client.tool.ToolCallbackProvider;
import org.springframework.ai.client.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider pixTools(PixService service) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(service)
                .build();
    }

}
