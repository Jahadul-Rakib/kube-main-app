
#ARG JDK=maven:3.6.3-openjdk-16-slim
#ARG JRE=openjdk:16-jre-slim-buster
#
#FROM ${JDK} AS build
#COPY src /home/app/src
#ADD pom.xml .
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package -DskipTests
#
#FROM ${JRE}
#COPY --from=build /home/app/target/kube_demo_main-0.0.1-SNAPSHOT.jar /usr/local/lib/kube_demo_main-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/kube_demo_main-0.0.1-SNAPSHOT.jar"]

FROM openjdk:16
VOLUME /tmp
COPY target/kube_demo_main-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]