# ---- Build Stage ----
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Kopiere Projektdateien
COPY . .

# Nutze den Gradle Wrapper aus deinem Projekt
RUN ./gradlew clean build -x test

# ---- Run Stage ----
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Kopiere fertiges JAR aus Build Stage
COPY --from=build /app/build/libs/*.jar app.jar

# Port-Variable (wird von Render/OCI gesetzt)
ENV PORT=8080
EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java","-jar","app.jar"]
