FROM infotechsoft/maven:3.9.11-jdk-25
ARG JAR_FILE=target/wh_t-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
