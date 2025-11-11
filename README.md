# Restaurant API - Documentación Técnica

## Descripción General

API REST para la gestión de un restaurante, que incluye funcionalidades para manejo de inventario, menú, órdenes, reservaciones y ventas.

## Tecnologías Utilizadas

### Core

- **Java 17**: Versión del lenguaje de programación utilizada
- **Spring Boot 3.2.2**: Framework principal para el desarrollo de la aplicación
- **Maven**: Herramienta de gestión y construcción del proyecto

### Base de Datos

- **MySQL**: Sistema de gestión de base de datos relacional
- **Spring Data JPA**: Para la persistencia de datos y mapeo objeto-relacional
- **Hibernate**: Como implementación de JPA
- **HikariCP**: Pool de conexiones (incluido con Spring Boot)

### Validación

- **Jakarta Validation**: Para la validación de datos

### Desarrollo

- **Spring Boot DevTools**: Herramientas de desarrollo para auto-recarga

## Estructura del Proyecto

### Modelos (models/)

Entidades JPA que representan las tablas de la base de datos:

- **User.java**: Gestión de usuarios y autenticación

  - Implementa UserDetails de Spring Security
  - Roles: ADMIN, CAMARERO, COCINERO

- **MenuItem.java & MenuItemCategory.java**: Gestión del menú

  - Categorización de items
  - Precios
  - Imágenes
  - Disponibilidad

- **Order.java & OrderItem.java**: Gestión de órdenes

  - Estados de orden
  - Items ordenados
  - Información del cliente
  - Tipo de orden

- **Reservation.java**: Gestión de reservaciones

  - Información del cliente
  - Fecha y hora
  - Estado de la reservación
  - Número de invitados

- **Sale.java & SaleItem.java**: Gestión de ventas

  - Registro de ventas
  - Items vendidos
  - Precios históricos

- **InventoryItem.java & MeasurementUnit.java**: Gestión de inventario
  - Stock
  - Unidades de medida
  - Precios unitarios

### DTOs (dto/)

Objetos de transferencia de datos organizados por funcionalidad:

- **inventory/**: DTOs para gestión de inventario
- **menu/**: DTOs para gestión del menú
- **reservation/**: DTOs para gestión de reservaciones
- **sale/**: DTOs para gestión de ventas

### Repositorios (repositories/)

Interfaces que extienden JpaRepository para operaciones CRUD:

- UserRepository
- MenuItemRepository
- MenuCategoryRepository
- OrderRepository
- ReservationRepository
- SaleRepository
- InventoryItemRepository
- MeasurementUnitRepository

### Servicios (services/)

Lógica de negocio de la aplicación:

- **MenuItemService & MenuCategoryService**: Gestión del menú
- **OrderService**: Procesamiento de órdenes
- **ReservationService**: Gestión de reservaciones
- **SaleService**: Procesamiento de ventas
- **InventoryService**: Gestión de inventario
- **MeasurementUnitService**: Gestión de unidades de medida

### Controladores (controllers/)

Endpoints REST organizados por funcionalidad:

- **AdminController**: `/api/admin/**` - Funciones administrativas
- **MenuController**: `/api/menu/**` - Gestión del menú
- **OrderController**: `/api/orders/**` - Gestión de órdenes
- **ReservationController**: `/api/reservations/**` - Gestión de reservaciones
- **SaleController**: `/api/sales/**` - Gestión de ventas
- **InventoryController**: `/api/inventory/**` - Gestión de inventario
- **KitchenController**: `/api/kitchen/**` - Funciones de cocina
- **MeasurementUnitController**: `/api/inventory/units/**` - Gestión de unidades

### Configuración (config/)

Clases de configuración:

- **SecurityConfig**: Configuración de seguridad y CORS
- **DataInitializer**: Inicialización de datos por defecto
- **InventoryDataInitializer**: Inicialización de datos de inventario
- **MenuDataInitializer**: Inicialización de datos del menú

## Configuración de Base de Datos

- Base de datos MySQL
- Nombre de la base de datos: restaurant_db
- Creación automática de la base de datos si no existe
- Actualización automática del esquema (hibernate.ddl-auto=update)
- Scripts SQL iniciales para tablas de inventario y menú

## Manejo de Archivos

- Ubicación de imágenes: /static/images/menu/
- Límite de tamaño de archivo: 10MB
- Límite de tamaño de solicitud: 10MB

## Logging

- Nivel DEBUG para Spring Security
- Nivel DEBUG para consultas SQL
- Nivel TRACE para parámetros de consultas SQL

## Características Adicionales

- Soporte para reinicio automático en desarrollo (DevTools)
- Validación de datos con Jakarta Validation
- Manejo global de excepciones
- Formateo de SQL para mejor legibilidad
- Referencias circulares permitidas (configuración temporal)
