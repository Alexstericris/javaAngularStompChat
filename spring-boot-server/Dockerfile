FROM maven:3.6-alpine AS build
LABEL authors="alex"

WORKDIR /src

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim

COPY --from=build /src/target/spring-boot-security-jwt-0.0.1-SNAPSHOT.jar /usr/local/lib/server.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/server.jar"]

<