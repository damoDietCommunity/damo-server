FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/-*-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
RUN mkdir -p ${STORAGE_PATH}/images
ENTRYPOINT ["java", "-Duser.timezone=\"Asia/Seoul\"", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]