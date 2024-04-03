FROM openjdk:17-jdk-slim as builder

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src/ ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/target/s1410-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "s1410-0.0.1-SNAPSHOT.jar"]
