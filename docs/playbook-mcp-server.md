# Playbook para Desenvolvimento do MCP Server

Este documento descreve os passos necessários para criar um MCP Server usando o [Spring AI MCP Server Boot Starter](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html).

## 1. Requisitos

- Java 17 ou superior
- Maven ou Gradle
- Spring Boot 3+

## 2. Dependências

Escolha o starter de acordo com o transporte desejado e adicione ao `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-mcp-server-spring-boot-starter</artifactId>
</dependency>
```

Para WebMVC (SSE):

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-mcp-server-webmvc</artifactId>
</dependency>
```

Para WebFlux (SSE reativo):

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-mcp-server-webflux</artifactId>
</dependency>
```

## 3. Configuração

Exemplo de propriedades no `application.yml` para um servidor WebMVC:

```yaml
spring:
  ai:
    mcp:
      server:
        name: webmvc-mcp-server
        version: 1.0.0
        type: SYNC
        sse-message-endpoint: /mcp/messages
        capabilities:
          tool: true
          resource: true
          prompt: true
          completion: true
```

As propriedades disponíveis incluem:

- `enabled` – habilita/desabilita o servidor (padrão `true`)
- `stdio` – habilita transporte STDIO (padrão `false`)
- `name` – nome do servidor
- `version` – versão do servidor
- `instructions` – mensagem de instruções para o cliente
- `type` – `SYNC` ou `ASYNC`
- `capabilities.*` – ativar/desativar features de tools, resources, prompts e completions

## 4. Implementação

Crie uma aplicação Spring Boot e registre beans que representam ferramentas, recursos ou prompts. O auto-configuration do starter irá exportá-los automaticamente.

```java
@Service
public class WeatherService {

    @Tool(description = "Obtém informações de clima por cidade")
    public String getWeather(String cityName) {
        // implementação
    }
}

@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(WeatherService service) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(service)
                .build();
    }
}
```

## 5. Integração com a API Pix

A [API Pix do Bacen](https://bacen.github.io/pix-api/) expõe diversos serviços
para iniciação e consulta de transações. Para disponibilizar essas operações via
MCP Server, registre ferramentas que chamem os respectivos endpoints REST.

Exemplo de serviço integrando com a API Pix utilizando `WebClient`:

```java
@Service
public class PixService {

    private final WebClient webClient;

    public PixService(WebClient.Builder builder,
                      @Value("${pix.base-url}") String baseUrl) {
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

@Bean
public ToolCallbackProvider pixTools(PixService service) {
    return MethodToolCallbackProvider.builder()
            .toolObjects(service)
            .build();
}
```

Configure o `pix.base-url` e as credenciais de acesso (certificados e OAuth)
de acordo com as especificações do Bacen. Esse valor pode ser sobrescrito
pela variável de ambiente `PIX_BASE_URL`.

## 6. Execução

Compile a aplicação e execute:

```bash
./mvnw spring-boot:run
```

## 7. Recursos adicionais

- [Documentação oficial](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html)
- [Model Context Protocol Specification](https://modelcontextprotocol.github.io/specification/)
- [Exemplos de uso no GitHub](https://github.com/spring-projects/spring-ai-examples)

