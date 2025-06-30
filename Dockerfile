# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build
COPY app/pom.xml app/pom.xml
COPY app/src app/src
WORKDIR /build/app
RUN mvn -B package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /build/app/target/mcpserver-apipix-0.0.1-SNAPSHOT.jar app.jar
ENV PIX_BASE_URL=https://pix.example.com
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
