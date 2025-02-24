# gRPC + Kotlin + Spring Boot Example

Este proyecto demuestra cómo configurar y usar gRPC con Kotlin en una aplicación Spring Boot. El servicio gRPC implementado es un servicio simple que devuelve la información de un usuario a partir de una solicitud.

## **Requisitos**

- **JDK 11+**
- **Maven 3.x+**
- **gRPC** (https://grpc.io/)
- **Spring Boot** (https://spring.io/projects/spring-boot)
- **Kotlin** (https://kotlinlang.org/)

## **Tecnologías Usadas**

- Kotlin
- Spring Boot
- gRPC
- Protocol Buffers (Protobuf)
- Maven para gestión de dependencias y construcción

---

## **Instrucciones para Ejecutar el Proyecto**

### **1. Clonar el Repositorio**

Primero, clona el repositorio en tu máquina local:

```bash
git clone https://github.com/BillyBohren/exercise3.git
cd grpc-kotlin-
```

## **2. Construir el Proyecto**

Usa Maven para construir el proyecto. Asegúrate de tener Maven instalado y configurado correctamente:

```bash
mvn clean install
```

### **3. Ejecutar la Aplicación**

Para ejecutar la aplicación Spring Boot, usa el siguiente comando:

```bash
mvn spring-boot:run
```