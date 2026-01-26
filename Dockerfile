# Etapa 1: Build com Maven e JDK 25
FROM maven:3.9.12-eclipse-temurin-25 AS build
WORKDIR /loajaApp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JRE 25
FROM eclipse-temurin:25-jre-jammy
WORKDIR /loajaApp
COPY --from=build /loajaApp/target/*.jar loajaApp.jar
RUN useradd -m -u 1000 -s /sbin/nologin nonroot
USER nonroot
EXPOSE 8080
CMD ["java", "-jar", "loajaApp.jar"]
