FROM maven as build
RUN mkdir -p /tmp/src
COPY . /tmp/src

WORKDIR /tmp/src
RUN mvn clean package -DskipTests


FROM fabric8/java-centos-openjdk8-jre as image
EXPOSE 8080
COPY --from=build "/tmp/src/target/redis*.jar" /deployments/app.jar