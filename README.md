# MCP Server API PIX

Este projeto demonstra a implementação de um servidor MCP (Model Context Protocol) para a API Pix do Banco Central do Brasil. A aplicação foi desenvolvida com Spring Boot 3 e Java 21 utilizando o *Spring AI MCP Server Boot Starter* para expor operações Pix como ferramentas MCP.

## Como funciona

O núcleo da aplicação está em `app/src/main`. A classe `McpServerApplication` inicializa o Spring Boot e registra as ferramentas disponíveis. Cada método público em `PixService` anotado com `@Tool` é exposto aos clientes via HTTP através do transport provider MVC. As chamadas ao Pix são delegadas para um endpoint externo configurado pela propriedade `pix.base-url` no `application.yml`.

## Arquitetura

A estrutura do repositório é organizada da seguinte forma:

- **app/** – código da aplicação Spring Boot.
- **docs/** – documentação e diagramas da arquitetura.
- **infra/** – arquivos de infraestrutura (Docker, Terraform, etc.).

A aplicação é *stateless* e encaminha as requisições para o serviço Pix configurado. O diagrama abaixo resume o fluxo principal:

```mermaid
digraph G {
  client -> mcpserver
  mcpserver -> pixapi
}
```

## Build e execução local

Para rodar localmente é necessário ter Java 21 e Maven instalados. Execute os comandos dentro do diretório `app`:

```bash
mvn spring-boot:run
```

É possível gerar o JAR e executá-lo manualmente:

```bash
mvn package
java -jar target/mcpserver-apipix-0.0.1-SNAPSHOT.jar
```

## Imagem Docker

O repositório inclui um `Dockerfile` multi-stage baseado na JRE Eclipse Temurin para gerar uma imagem enxuta. Para construir a imagem e subir o container:

```bash
docker build -t mcpserver-apipix .
docker run --rm -p 8080:8080 mcpserver-apipix
```
