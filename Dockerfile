# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le JAR compilé dans le conteneur
COPY target/my_canevas-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port utilisé par l'application
EXPOSE 8080

# Définir la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
