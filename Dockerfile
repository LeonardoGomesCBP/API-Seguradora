FROM openjdk:11
EXPOSE 8080
ADD target/apiseguradora.jar apiseguradora.jar
ENTRYPOINT ["java","-jar","/apiseguradora.jar"]
