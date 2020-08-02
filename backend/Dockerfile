### Build ###
FROM maven:3.6.3-jdk-11-slim as BUILDER
COPY . /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package

### Run ###
FROM openjdk:16-jdk-alpine
WORKDIR /
COPY --from=BUILDER /usr/src/app/target/backend*.jar /backend.jar
EXPOSE 8080
CMD ["java","-jar","backend.jar"]
