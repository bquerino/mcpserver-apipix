package com.example.mcpserver;

import org.springframework.ai.mcp.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PixService {

    private final WebClient webClient;

    public PixService(WebClient.Builder builder,
                      @Value("${pix.base-url:https://pix.example.com}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    @Tool(description = "Consulta uma transação Pix pelo e2eid")
    public Mono<String> consultarPix(String e2eid) {
        return webClient.get()
                .uri("/api/v2/pix/{e2eid}", e2eid)
                .retrieve()
                .bodyToMono(String.class);
    }
}
