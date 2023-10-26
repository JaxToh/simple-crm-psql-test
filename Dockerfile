# FROM maven:3.8.2-openjdk-17 AS maven

# WORKDIR /app

# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./

# RUN chmod +x ./mvnw

# COPY src ./src

# CMD ["./mvnw", "spring-boot:run"]


FROM maven:3.8.2-openjdk-17 AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
COPY src ./src
RUN ./mvnw clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/your-application.jar ./your-application.jar
CMD ["java", "-jar", "your-application.jar"]