FROM openjdk:8

MAINTAINER nizamiislamovs@gmail.com

#Debugging
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
EXPOSE 8000

VOLUME /tmp
EXPOSE 8082

ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.soap.soapclient.SoapclientApplication"]
