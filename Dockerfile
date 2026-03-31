FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine

RUN useradd -m -u 1000 appuser

WORKDIR /app

COPY --from=build --chown=appuser:appuser /app/target/shop-0.0.1-SNAPSHOT.jar /app/app.jar

ENV TZ="America/Sao_Paulo"
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxRAMPercentage=75.0"

USER appuser

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD java -version

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
