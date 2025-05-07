# API REST de Pedidos

Este proyecto implementa una API REST en Spring Boot que recibe pedidos en formato JSON los transforma a XML tipo SOAP y consume un servicio externo con Mocky.io y transforma la respuesta XML nuevamente a JSON

---

## Requisitos del sistema

- Java 17
- Maven 3.x
- Docker

---

## ¿Cómo ejecutar la aplicación?

###  Opción 1: Ejecutar localmente (sin Docker)

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repo.git
   cd tu-repo
2. compila el proyecto
   ```
   mvn clean package
3. Ejecutar app
   ```
   java -jar target/prueba-seti-0.0.1-SNAPSHOT.jar

###  Opción 1: Ejecutar localmente (con Docker)
1. compila el proyecto
   ```
   mvn clean package

2. Construir imagen
   ```
   docker build -t test/prueba-seti .
   
3.  Lanzar contenedor
    ```
    docker run -p 8080:8080 test/prueba-seti

---
USO API
Endpoint disponible
```
http://localhost:8080/api/pedidos
```

Request
```
{
  "enviarPedido": {
    "numPedido": "75630275",
    "cantidadPedido": "1",
    "codigoEAN": "00110000765191002104587",
    "nombreProducto": "Armario INVAL",
    "numDocumento": "1113987400",
    "direccion": "CR 72B 45 12 APT 301"
  }
}
```
Response
```
{
  "codigoEnvio": "80375472",
  "estado": "Entregado exitosamente al cliente"
}

