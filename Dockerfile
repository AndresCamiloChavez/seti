# Imagen base de Java con JDK 17 (ajústalo si usas otra versión)
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR construido en el contenedor
COPY target/prueba-seti-0.0.1-SNAPSHOT.jar app.jar


# Puerto expuesto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
