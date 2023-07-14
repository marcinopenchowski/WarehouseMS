FROM eclipse-temurin:17.0.6_10-jdk
MAINTAINER wms.com
RUN apt-get update && \
    apt-get install -y maven
COPY ../../pom.xml /build/
COPY src /build/src/
WORKDIR /build
RUN mvn package
COPY ../../target/WarehouseMS-0.0.1-SNAPSHOT.jar warehousems.jar
ENTRYPOINT ["java","-jar","/warehousems.jar"]