# Etapa 1: Build com Maven e JDK 21
FROM maven:3.9.7-eclipse-temurin-21-alpine AS build
WORKDIR /loajaApp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JRE 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /loajaApp
COPY --from=build /loajaApp/target/*.jar loajaApp.jar
EXPOSE 8080
CMD ["java", "-jar", "loajaApp.jar"]
