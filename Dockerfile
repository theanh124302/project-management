FROM openjdk:22-jdk
WORKDIR /app
COPY out/artifacts/ProjectManagement_jar ProjectManagement
EXPOSE 8080
CMD ["java", "-jar", "ProjectManagement/ProjectManagement.jar"]