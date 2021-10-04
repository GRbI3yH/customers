FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/customers-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

