FROM openjdk:17-alpine
COPY target/*.jar app.jar
EXPOSE 10000
CMD ["java", "-jar", "-Dserver.port=10000", "app.jar"]