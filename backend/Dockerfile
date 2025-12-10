FROM eclipse-temurin:25-jre
WORKDIR /app
# direkte Kopie der gebauten JAR (Wildcard funktioniert im Build-Context)
COPY build/libs/*-boot.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
