FROM openjdk:21-jdk-slim
COPY . .
RUN mvn clean package

LABEL authors="ANPHUOC"

FROM openjdk:21-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
