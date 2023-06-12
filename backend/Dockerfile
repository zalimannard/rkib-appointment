FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
RUN mkdir /opt/rkib-appointment-backend
COPY ${JAR_FILE} /opt/rkib-appointment-backend/rkib-appointment-backend.jar
ENTRYPOINT ["java","-jar","/opt/rkib-appointment-backend/rkib-appointment-backend.jar"]
