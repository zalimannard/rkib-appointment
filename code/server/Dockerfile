FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
RUN mkdir /opt/rkib-appointment-server
COPY ${JAR_FILE} /opt/rkib-appointment-server/rkib-appointment-server.jar
ENTRYPOINT ["java","-jar","/opt/rkib-appointment-server/rkib-appointment-server.jar"]
