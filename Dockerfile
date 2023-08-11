FROM eclipse-temurin:17.0.6_10-jdk
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service
RUN mvn clean package
CMD ["mvn","spring-boot:run"]