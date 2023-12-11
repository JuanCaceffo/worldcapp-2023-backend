# Usar alpine:latest como imagen base
FROM alpine:latest

# Instalar OpenJDK 17
RUN apk --no-cache add openjdk17

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar todos los archivos en el contenedor
COPY . .

# Dar permisos de ejecución al archivo gradlew
RUN chmod +x gradlew

# Ejecutar Gradle para construir la aplicación
RUN sed -i 's/\r$//' gradlew
RUN ./gradlew build

# Exponer el puerto 8080
EXPOSE 8000

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "build/libs/WorldCapp08-0.0.1-SNAPSHOT.war"]
