# Estructura de la Base de Datos

## Esquema General

Base de datos MySQL para un sistema de gestión de restaurante que maneja inventario, menú, órdenes, reservaciones y ventas.

## Tablas

### Inventario

#### measurement_units

Almacena las unidades de medida para los items del inventario.

```sql
CREATE TABLE measurement_units (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,      -- Nombre de la unidad (ej: Kilogramos)
    symbol VARCHAR(10) NOT NULL UNIQUE,    -- Símbolo de la unidad (ej: kg)
    created_at TIMESTAMP,                  -- Fecha de creación
    updated_at TIMESTAMP                   -- Fecha de última actualización
)
```

Datos iniciales: kg, g, L, ml, u, doc, caja, paq

#### inventory_items

Gestiona el inventario de productos.

```sql
CREATE TABLE inventory_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,     -- Nombre del item
    unit_price DECIMAL(10,2) NOT NULL,     -- Precio unitario
    stock DOUBLE NOT NULL,                 -- Cantidad en stock
    measurement_unit_id BIGINT,            -- Referencia a la unidad de medida
    created_at TIMESTAMP,                  -- Fecha de creación
    last_updated TIMESTAMP                 -- Fecha de última actualización
)
```

#### stock_history

Registra los cambios en el inventario.

```sql
CREATE TABLE stock_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_item_id BIGINT NOT NULL,     -- Item afectado
    operation_type ENUM('INCREASE', 'DECREASE'), -- Tipo de operación
    amount DOUBLE NOT NULL,                -- Cantidad modificada
    previous_stock DOUBLE NOT NULL,        -- Stock anterior
    new_stock DOUBLE NOT NULL,             -- Nuevo stock
    user_id BIGINT,                        -- Usuario que realizó la operación
    created_at TIMESTAMP                   -- Fecha de la operación
)
```

### Menú

#### menu_categories

Categorías de items del menú.

```sql
CREATE TABLE menu_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE      -- Nombre de la categoría
)
```

Categorías predefinidas: Hamburguesa, Pizza, Bebida, Complemento, Postre, Combo

#### menu_items

Items disponibles en el menú.

```sql
CREATE TABLE menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,            -- Nombre del item
    price DECIMAL(10,2) NOT NULL,          -- Precio
    description TEXT,                      -- Descripción
    image_path VARCHAR(255),               -- Ruta de la imagen
    category_id BIGINT NOT NULL,           -- Categoría del item
    available BOOLEAN DEFAULT true,        -- Disponibilidad
    created_at DATETIME,                   -- Fecha de creación
    updated_at DATETIME                    -- Fecha de actualización
)
```

### Reservaciones

#### reservations

Gestiona las reservaciones de mesas.

```sql
CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,            -- Nombre del cliente
    phone VARCHAR(20) NOT NULL,            -- Teléfono
    reservation_date DATE NOT NULL,        -- Fecha de la reserva
    reservation_time TIME NOT NULL,        -- Hora de la reserva
    number_of_guests INT NOT NULL,         -- Número de personas
    status VARCHAR(20) DEFAULT 'PENDING',  -- Estado (PENDING|CONFIRMED|CANCELLED)
    created_at TIMESTAMP,                  -- Fecha de creación
    updated_at TIMESTAMP                   -- Fecha de actualización
)
```

### Ventas

#### sales

Registro de ventas.

```sql
CREATE TABLE sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,            -- Identificador de la venta
    description TEXT,                      -- Descripción opcional
    sale_date TIMESTAMP NOT NULL,          -- Fecha de la venta
    total_price DECIMAL(10,2) NOT NULL,    -- Monto total
    created_at TIMESTAMP,                  -- Fecha de creación
    updated_at TIMESTAMP                   -- Fecha de actualización
)
```

#### sale_items

Items incluidos en cada venta.

```sql
CREATE TABLE sale_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT NOT NULL,              -- Venta asociada
    menu_item_id BIGINT NOT NULL,         -- Item del menú vendido
    price_at_time DECIMAL(10,2) NOT NULL, -- Precio al momento de la venta
    created_at TIMESTAMP                   -- Fecha de creación
)
```

## Índices

### Inventario

- `idx_inventory_items_stock`: Optimiza búsquedas por nivel de stock
- `idx_inventory_items_name`: Optimiza búsquedas por nombre
- `idx_stock_history_item_date`: Optimiza búsquedas de historial por item y fecha

### Menú

- `idx_menu_items_category`: Optimiza búsquedas por categoría
- `idx_menu_items_available`: Optimiza filtrado por disponibilidad
- `idx_menu_items_name`: Optimiza búsquedas por nombre

## Relaciones

### Inventario

- `inventory_items.measurement_unit_id` → `measurement_units.id`
- `stock_history.inventory_item_id` → `inventory_items.id`
- `stock_history.user_id` → `users.id`

### Menú

- `menu_items.category_id` → `menu_categories.id`

### Ventas

- `sale_items.sale_id` → `sales.id`
- `sale_items.menu_item_id` → `menu_items.id`

## Características Especiales

### Timestamps Automáticos

Todas las tablas incluyen campos de auditoría:

- `created_at`: Se establece automáticamente al crear el registro
- `updated_at`: Se actualiza automáticamente al modificar el registro

### Valores por Defecto

- `menu_items.available`: true
- `reservations.status`: 'PENDING'

### Restricciones de Unicidad

- Nombres de unidades de medida y símbolos
- Nombres de items de inventario
- Nombres de categorías de menú

### Campos NOT NULL

Se han definido campos obligatorios en todas las tablas para mantener la integridad de los datos.

### Tipos de Datos

- Precios: DECIMAL(10,2) para precisión en valores monetarios
- Stock: DOUBLE para permitir fracciones en inventario
- Fechas: TIMESTAMP para registro preciso de momentos
- Textos: VARCHAR con límites apropiados según el uso

## Notas de Implementación

- La base de datos utiliza InnoDB como motor de almacenamiento
- Soporta transacciones ACID
- Utiliza UTF-8 para soporte multilingüe
- Los índices están optimizados para las consultas más comunes
- Las relaciones están protegidas con claves foráneas
