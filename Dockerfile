# Usar openjdk:17-jdk-buster como imagen base
FROM openjdk:17-jdk-buster

# Instalar dos2unix
RUN apt-get update && apt-get install -y dos2unix

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar todos los archivos en el contenedor
COPY . .

# Convertir los finales de línea del archivo gradlew a Unix
RUN dos2unix gradlew
RUN chmod +x gradlew

# Ejecutar Gradle para construir la aplicación
RUN ./gradlew build

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "build/libs/WorldCapp08-0.0.1-SNAPSHOT.war"]
