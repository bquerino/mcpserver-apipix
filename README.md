# MCP Server API PIX

This repository contains a Spring Boot implementation of an MCP Server for the
Pix API regulated by the Brazilian Central Bank (Bacen). The server is built
using Java 21 and follows the guidelines from the [Model Context Protocol](https://modelcontextprotocol.github.io/specification/) and
Spring AI MCP Server Boot Starter.

## Repository Structure

- **app/** - Spring Boot application code for the MCP Server.
- **docs/** - Project documentation written in Markdown with Mermaid diagrams.
- **infra/** - Infrastructure and deployment resources such as Docker Compose and Terraform.

The initial version exposes the Pix operations as MCP tools using the MVC server
transport provider.
