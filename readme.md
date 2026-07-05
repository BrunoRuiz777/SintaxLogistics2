# Syntax Logistics - Backend API 📦

Plataforma de gestión logística integral diseñada para optimizar los servicios de mensajería urbana. Este repositorio contiene la API RESTful que da soporte al núcleo del negocio: cotización de envíos, generación de guías, rastreo de paquetes y gestión de usuarios.

## 🛠️ Stack Tecnológico

Este proyecto está construido con una arquitectura de múltiples capas utilizando las siguientes tecnologías:

*   **Java:** 21
*   **Framework:** Spring Boot 4.1.0
*   **Base de Datos:** MySQL 8+
*   **ORM:** Spring Data JPA / Hibernate
*   **Seguridad:** Spring Security + JWT (JSON Web Tokens 0.12.6)
*   **Documentación API:** Springdoc OpenAPI (Swagger) 2.3.0
*   **Utilerías:** Lombok

## 🚀 Requisitos Previos

Para levantar este proyecto en tu entorno local, asegúrate de tener instalado:
*   Java Development Kit (JDK) 21
*   MySQL Server (o Workbench) corriendo en el puerto 3306
*   Git

## ⚙️ Configuración del Entorno Local

1. **Clona el repositorio:**
   ```bash
   git clone [https://github.com/TU-USUARIO/syntax-logistics-backend.git](https://github.com/TU-USUARIO/syntax-logistics-backend.git)

1. **Cambiar username y password:**
*  Mantener en el repositorio un application.properties con variables de entorno, cada integrante debe reemplazar esos valores por los
*  de su entorno local.
