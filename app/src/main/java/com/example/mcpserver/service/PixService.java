package com.example.mcpserver.service;

import org.springframework.ai.client.tool.Tool;
import org.springframework.stereotype.Service;

/**
 * Serviço que fornece dados mockados da API Pix.
 */
@Service
public class PixService {

    public PixService() {
    }

    @Tool(description = "Criar recorrência de pagamento")
    public String criarRecorrencia(String recJson) {
        return "{\"idRecorrencia\":\"rec_123\",\"status\":\"CRIADA\"}";
    }

    @Tool(description = "Consultar recorrência")
    public String consultarRecorrencia(String idRec) {
        return "{\"idRecorrencia\":\"" + idRec + "\",\"status\":\"CONCLUIDA\"}";
    }

    @Tool(description = "Revisar recorrência")
    public String revisarRecorrencia(String idRec, String recJson) {
        return "{\"idRecorrencia\":\"" + idRec + "\",\"status\":\"REVISADA\"}";
    }

    @Tool(description = "Listar recorrências")
    public String listarRecorrencias(String inicio, String fim) {
        return "{\"lista\":[{\"idRecorrencia\":\"rec_123\"}]}";
    }

    @Tool(description = "Criar solicitação de recorrência")
    public String criarSolicitacao(String solicJson) {
        return "{\"idSolicRec\":\"sol_123\",\"status\":\"CRIADA\"}";
    }

    @Tool(description = "Consultar solicitação de recorrência")
    public String consultarSolicitacao(String idSolicRec) {
        return "{\"idSolicRec\":\"" + idSolicRec + "\",\"status\":\"CONCLUIDA\"}";
    }

    @Tool(description = "Revisar solicitação de recorrência")
    public String revisarSolicitacao(String idSolicRec, String solicJson) {
        return "{\"idSolicRec\":\"" + idSolicRec + "\",\"status\":\"REVISADA\"}";
    }

    @Tool(description = "Configurar webhook de recorrências")
    public String configurarWebhookRec(String webhookJson) {
        return "{\"webhook\":\"https://exemplo.com/webhook\"}";
    }

    @Tool(description = "Consultar webhook de recorrências")
    public String consultarWebhookRec() {
        return "{\"webhook\":\"https://exemplo.com/webhook\"}";
    }

    @Tool(description = "Cancelar webhook de recorrências")
    public void cancelarWebhookRec() {
        // operação simulada
    }
}
