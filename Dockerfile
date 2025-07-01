# Étape 1 : builder le projet Maven
FROM maven:3.9.1-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copier les fichiers pom.xml et sources
COPY pom.xml .
COPY src ./src

# Build du projet sans tests pour gagner du temps
RUN mvn clean package -DskipTests

# Étape 2 : créer l’image finale avec le JAR généré
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier le JAR depuis l'étape précédente
COPY --from=build /app/target/my_canevas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
