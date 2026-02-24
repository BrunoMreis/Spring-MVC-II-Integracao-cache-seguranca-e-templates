# Etapa 1: Build com Maven e JDK 25
FROM maven:3.9.12-eclipse-temurin-25 AS build
WORKDIR /loajaApp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com JRE 25
FROM dhi.io/eclipse-temurin:25-alpine3.23
WORKDIR /loajaApp
COPY --from=build /loajaApp/target/*.jar loajaApp.jar
EXPOSE 8080 5005
CMD ["java", "-Djava.io.tmpdir=/loajaApp", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "loajaApp.jar"]
