package com.example.mcpserver;

import org.springframework.ai.mcp.tool.ToolCallbackProvider;
import org.springframework.ai.mcp.tool.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
