# ---- Build Stage ----
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Zuerst nur die Gradle-Dateien kopieren (für Dependency-Caching)
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle gradle

# Dependencies herunterladen (wird gecached, solange sich build.gradle nicht ändert)
RUN ./gradlew build -x test --continue || true

# Danach den Rest des Codes kopieren
COPY . .

# Jetzt das eigentliche Projekt bauen
RUN ./gradlew clean build -x test

# ---- Run Stage ----
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Kopiere fertiges JAR aus Build Stage
COPY --from=build /app/build/libs/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
