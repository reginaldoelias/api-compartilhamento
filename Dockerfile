FROM openjdk:17
RUN mkdir compartilhamento
COPY /compartilhamento/ compartilhamento/
WORKDIR /app
COPY target/sharing-1.0.jar /app/sharing.jar
CMD ["java", "-jar", "sharing.jar"]
EXPOSE 8080