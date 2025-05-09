# Backend Dockerfile

# 1단계 : 빌드
FROM gradle:8.6-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# 2단계 : 런타임
FROM openjdk:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]