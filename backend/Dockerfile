FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR_FILE=build/libs/*-boot.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
