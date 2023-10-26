FROM maven:3.8.2-openjdk-17

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN chmod +x ./mvnw

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]