package com.example.mcpserver.service;

import org.springframework.ai.client.tool.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Serviço que encapsula chamadas à API Pix.
 */
@Service
public class PixService {

    private final WebClient webClient;

    public PixService(WebClient.Builder builder, @Value("${pix.base-url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    @Tool(description = "Criar recorrência de pagamento")
    public Mono<String> criarRecorrencia(String recJson) {
        return webClient.post()
                .uri("/rec")
                .bodyValue(recJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Consultar recorrência")
    public Mono<String> consultarRecorrencia(String idRec) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rec/{idRec}").build(idRec))
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Revisar recorrência")
    public Mono<String> revisarRecorrencia(String idRec, String recJson) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/rec/{idRec}").build(idRec))
                .bodyValue(recJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Listar recorrências")
    public Mono<String> listarRecorrencias(String inicio, String fim) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rec")
                        .queryParam("inicio", inicio)
                        .queryParam("fim", fim)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Criar solicitação de recorrência")
    public Mono<String> criarSolicitacao(String solicJson) {
        return webClient.post()
                .uri("/solicrec")
                .bodyValue(solicJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Consultar solicitação de recorrência")
    public Mono<String> consultarSolicitacao(String idSolicRec) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/solicrec/{id}").build(idSolicRec))
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Revisar solicitação de recorrência")
    public Mono<String> revisarSolicitacao(String idSolicRec, String solicJson) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/solicrec/{id}").build(idSolicRec))
                .bodyValue(solicJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Configurar webhook de recorrências")
    public Mono<String> configurarWebhookRec(String webhookJson) {
        return webClient.put()
                .uri("/webhookrec")
                .bodyValue(webhookJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Consultar webhook de recorrências")
    public Mono<String> consultarWebhookRec() {
        return webClient.get()
                .uri("/webhookrec")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Tool(description = "Cancelar webhook de recorrências")
    public Mono<Void> cancelarWebhookRec() {
        return webClient.delete()
                .uri("/webhookrec")
                .retrieve()
                .bodyToMono(Void.class);
    }
}
