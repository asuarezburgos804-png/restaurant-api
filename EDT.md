# Estructura de Desglose del Trabajo (EDT) - Sistema de Gestión de Restaurante

## 1. Sistema de Gestión de Restaurante

### 1.1 Gestión de Seguridad

#### 1.1.1 Autenticación

- Implementación de JWT
- Gestión de tokens
- Manejo de roles (ADMIN, CAMARERO, COCINERO)
- Registro de usuarios
- Login de usuarios

#### 1.1.2 Autorización

- Control de acceso basado en roles
- Validación de permisos
- Protección de endpoints
- Configuración de CORS

### 1.2 Gestión de Menú

#### 1.2.1 Categorías

- CRUD de categorías
- Validación de nombres únicos
- Categorías predefinidas

#### 1.2.2 Items del Menú

- CRUD de items
- Gestión de imágenes
- Control de disponibilidad
- Precios y descripciones
- Asociación con categorías

### 1.3 Gestión de Inventario

#### 1.3.1 Unidades de Medida

- CRUD de unidades
- Validación de símbolos únicos
- Unidades predefinidas

#### 1.3.2 Items de Inventario

- CRUD de items
- Control de stock
- Precios unitarios
- Asociación con unidades de medida

#### 1.3.3 Historial de Stock

- Registro de operaciones
- Trazabilidad de cambios
- Auditoría de usuarios

### 1.4 Gestión de Órdenes

#### 1.4.1 Creación de Órdenes

- Registro de cliente
- Selección de items
- Cálculo de totales
- Tipos de orden (DINE_IN, TAKEOUT, DELIVERY)

#### 1.4.2 Procesamiento de Órdenes

- Flujo de estados
- Asignación a cocina
- Actualización de estado
- Notificaciones

### 1.5 Gestión de Reservaciones

#### 1.5.1 Creación de Reservas

- Datos del cliente
- Fecha y hora
- Número de personas
- Validación de disponibilidad

#### 1.5.2 Administración de Reservas

- Confirmación/Cancelación
- Modificación de datos
- Historial de cambios
- Estados de reserva

### 1.6 Gestión de Ventas

#### 1.6.1 Registro de Ventas

- Creación de ventas
- Items vendidos
- Precios históricos
- Totales

#### 1.6.2 Reportes de Ventas

- Ventas por período
- Productos más vendidos
- Ingresos totales
- Estadísticas

### 1.7 Administración del Sistema

#### 1.7.1 Configuración

- Parámetros del sistema
- Configuración de JWT
- Configuración de base de datos
- Manejo de archivos

#### 1.7.2 Monitoreo

- Logging del sistema
- Auditoría de operaciones
- Control de errores
- Rendimiento

### 1.8 Infraestructura

#### 1.8.1 Base de Datos

- Esquema relacional
- Índices y optimizaciones
- Respaldos
- Migraciones

#### 1.8.2 API REST

- Endpoints
- Documentación
- Validaciones
- Manejo de errores

#### 1.8.3 Seguridad

- SSL/TLS
- Protección contra ataques
- Validación de datos
- Sanitización de entradas

### 1.9 Documentación

#### 1.9.1 Técnica

- Arquitectura del sistema
- Estructura de la base de datos
- Configuración del entorno
- Guías de desarrollo

#### 1.9.2 API

- Endpoints disponibles
- Formatos de request/response
- Ejemplos de uso
- Autenticación

#### 1.9.3 Usuario

- Manual de usuario
- Guías de operación
- Procedimientos
- Solución de problemas

### 1.10 Pruebas

#### 1.10.1 Unitarias

- Servicios
- Controladores
- Modelos
- Utilidades

#### 1.10.2 Integración

- Flujos completos
- Interacción entre módulos
- Persistencia de datos
- Seguridad

#### 1.10.3 Sistema

- Rendimiento
- Carga
- Seguridad
- Recuperación

## 2. Entregables

### 2.1 Software

- Código fuente
- Ejecutables
- Scripts de base de datos
- Archivos de configuración

### 2.2 Documentación

- Documentación técnica
- Documentación de API
- Manuales de usuario
- Guías de instalación

### 2.3 Pruebas

- Plan de pruebas
- Casos de prueba
- Informes de resultados
- Evidencias

## 3. Gestión del Proyecto

### 3.1 Planificación

- Cronograma
- Recursos
- Riesgos
- Dependencias

### 3.2 Seguimiento

- Control de avance
- Gestión de cambios
- Resolución de problemas
- Informes de estado

### 3.3 Calidad

- Estándares de código
- Revisiones
- Métricas
- Mejora continua
