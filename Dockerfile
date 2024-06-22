# Etapa 1: Construcción
FROM --platform=linux/amd64 gradle:8.2.1-jdk17 AS build
WORKDIR /app

# Copiar archivos de configuración de Gradle y la aplicación para la caché de dependencias
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Cambiar temporalmente a root para resolver problemas de permisos (para depuración)
USER root

# Descargar dependencias antes de copiar el código fuente para aprovechar la caché de Docker
RUN ./gradlew build || return 0

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN ./gradlew clean bootJar

# Cambiar de nuevo a un usuario no privilegiado si es necesario (por ejemplo, 'nobody')
USER nobody


# Etapa 2: Ejecución
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-jammy

# Agregar un usuario no privilegiado
USER nobody

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el archivo .jar generado desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto que la aplicación utilizará
EXPOSE 9500

# Copiar archivos de configuración
COPY src/main/resources/application.properties /app/config/

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/app/config/application.properties"]
