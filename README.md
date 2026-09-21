# SOFTBECX

Sistema administrativo web desarrollado para la gestión integral de una empresa.

## 📋 Descripción

SOFTBECX es una aplicación web orientada a la administración de una empresa, permitiendo gestionar usuarios, roles, productos o servicios, clientes, pedidos, autenticación y diferentes procesos administrativos desde una plataforma centralizada.

El proyecto está siendo desarrollado como parte del proceso de formación del SENA.

## 🎯 Objetivo

Desarrollar un sistema administrativo que permita centralizar y facilitar la gestión de los principales procesos de una empresa, proporcionando una plataforma segura, organizada y fácil de utilizar.

## 🚀 Funcionalidades

### 🔐 Autenticación

- Inicio de sesión.
- Validación de usuario.
- Encriptación de contraseñas.
- Autenticación mediante JWT.
- Protección de endpoints con Spring Security.
- Cierre de sesión.
- Invalidación de tokens.
- Cambio de contraseña.
- Recuperación de contraseña.

### 👤 Usuarios

- Crear usuarios.
- Consultar usuarios.
- Editar usuarios.
- Eliminar usuarios.
- Asignar roles.
- Gestión de contraseñas.

### 🛡️ Roles

- Crear roles.
- Consultar roles.
- Editar roles.
- Eliminar roles.

### 📦 Productos y servicios

- Crear productos o servicios.
- Consultar productos o servicios.
- Editar productos o servicios.
- Activar o desactivar productos o servicios.
- Gestionar precios, descripciones, imágenes y categorías.

### 👥 Clientes

- Registrar clientes.
- Consultar clientes.
- Editar clientes.
- Eliminar clientes.
- Consultar historial de pedidos.

### 🛒 Pedidos

- Crear pedidos.
- Consultar pedidos.
- Consultar detalles.
- Cambiar estados.
- Asociar pedidos con clientes.
- Asociar productos o servicios.
- Calcular totales.

### 📊 Dashboard

- Resumen de ventas.
- Cantidad de clientes.
- Cantidad de productos o servicios.
- Pedidos recientes.
- Estadísticas básicas.

### 🌐 Página web

- Información de la empresa.
- Logo.
- Banner.
- Imágenes.
- Textos.
- Servicios.
- Información de contacto.
- Redes sociales.

### ⚙️ Configuración

- Datos de la empresa.
- Información de contacto.
- Configuración de cuenta.
- Roles y permisos.
- Preferencias del sistema.

## 🧰 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- BCrypt
- MySQL
- Maven
- IntelliJ IDEA
- Postman

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura basada en capas:

```text
src
└── main
    └── java
        └── com.softbecx.softbecx
            ├── controller
            ├── dto
            ├── model
            ├── repository
            ├── security
            └── service