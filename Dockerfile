FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/CoporateTraining-0.0.1-SNAPSHOT.jar CoporateTraining.jar
EXPOSE 8687
ENTRYPOINT ["java", "-jar","CoporateTraining.jar"]