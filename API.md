# API Documentation

## Autenticación

### POST /api/auth/register

Registra un nuevo usuario en el sistema.

```json
{
  "username": "string",
  "password": "string",
  "role": "ADMIN|CAMARERO|COCINERO"
}
```

### POST /api/auth/login

Inicia sesión y obtiene un token JWT.

```json
{
  "username": "string",
  "password": "string"
}
```

## Menú

### GET /api/menu/categories

Obtiene todas las categorías del menú.

### POST /api/menu/categories

Crea una nueva categoría del menú.

```json
{
  "name": "string"
}
```

### GET /api/menu/items

Obtiene todos los items del menú.

### GET /api/menu/items/{id}

Obtiene un item específico del menú.

### POST /api/menu/items

Crea un nuevo item del menú.

```json
{
  "name": "string",
  "price": "number",
  "categoryId": "number",
  "description": "string",
  "available": "boolean",
  "image": "file"
}
```

### PUT /api/menu/items/{id}

Actualiza un item del menú.

```json
{
  "name": "string",
  "price": "number",
  "categoryId": "number",
  "description": "string",
  "available": "boolean",
  "image": "file"
}
```

## Inventario

### GET /api/inventory/items

Obtiene todos los items del inventario.

### GET /api/inventory/items/{id}

Obtiene un item específico del inventario.

### POST /api/inventory/items

Crea un nuevo item en el inventario.

```json
{
  "name": "string",
  "unitPrice": "number",
  "stock": "number",
  "measurementUnitId": "number"
}
```

### PUT /api/inventory/items/{id}

Actualiza un item del inventario.

```json
{
  "name": "string",
  "unitPrice": "number",
  "stock": "number",
  "measurementUnitId": "number"
}
```

### POST /api/inventory/items/{id}/stock

Actualiza el stock de un item.

```json
{
  "quantity": "number",
  "operation": "ADD|SUBTRACT"
}
```

### GET /api/inventory/units

Obtiene todas las unidades de medida.

### POST /api/inventory/units

Crea una nueva unidad de medida.

```json
{
  "name": "string",
  "symbol": "string"
}
```

## Órdenes

### GET /api/orders

Obtiene todas las órdenes.

### GET /api/orders/{id}

Obtiene una orden específica.

### POST /api/orders

Crea una nueva orden.

```json
{
  "tableNumber": "number",
  "type": "DINE_IN|TAKEOUT|DELIVERY",
  "customerName": "string",
  "customerPhone": "string",
  "items": [
    {
      "menuItemId": "number",
      "quantity": "number",
      "notes": "string"
    }
  ]
}
```

### PUT /api/orders/{id}/status

Actualiza el estado de una orden.

```json
{
  "status": "PENDING|IN_PROGRESS|COMPLETED|CANCELLED"
}
```

## Reservaciones

### GET /api/reservations

Obtiene todas las reservaciones.

### GET /api/reservations/{id}

Obtiene una reservación específica.

### POST /api/reservations

Crea una nueva reservación.

```json
{
  "name": "string",
  "phone": "string",
  "date": "date",
  "time": "time",
  "guests": "number"
}
```

### PUT /api/reservations/{id}

Actualiza una reservación.

```json
{
  "name": "string",
  "phone": "string",
  "date": "date",
  "time": "time",
  "guests": "number",
  "status": "PENDIENTE|CONFIRMADO|CANCELADO"
}
```

## Ventas

### GET /api/sales

Obtiene todas las ventas.

### GET /api/sales/{id}

Obtiene una venta específica.

### POST /api/sales

Registra una nueva venta.

```json
{
  "name": "string",
  "description": "string",
  "items": [
    {
      "menuItemId": "number",
      "priceAtTime": "number"
    }
  ]
}
```

## Cocina

### GET /api/kitchen/orders

Obtiene las órdenes pendientes para la cocina.

### PUT /api/kitchen/orders/{id}/status

Actualiza el estado de una orden en la cocina.

```json
{
  "status": "IN_PROGRESS|COMPLETED"
}
```

## Administración

### GET /api/admin/users

Obtiene todos los usuarios (solo admin).

### PUT /api/admin/users/{id}/role

Actualiza el rol de un usuario (solo admin).

```json
{
  "role": "ADMIN|CAMARERO|COCINERO"
}
```

### GET /api/admin/sales/report

Obtiene un reporte de ventas (solo admin).
Parámetros de consulta:

- startDate: fecha inicial (YYYY-MM-DD)
- endDate: fecha final (YYYY-MM-DD)

### GET /api/admin/inventory/report

Obtiene un reporte de inventario (solo admin).
Parámetros de consulta:

- lowStock: boolean (items con stock bajo)
- category: string (filtrar por categoría)

## Notas Adicionales

### Autenticación

- Todos los endpoints (excepto los marcados como públicos) requieren un token JWT válido en el header:

```
Authorization: Bearer <token>
```

### Respuestas de Error

```json
{
  "timestamp": "datetime",
  "status": "number",
  "error": "string",
  "message": "string",
  "path": "string"
}
```

### Códigos de Estado HTTP

- 200: OK
- 201: Created
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error

### Paginación

Los endpoints que retornan listas soportan paginación mediante parámetros de consulta:

- page: número de página (default: 0)
- size: tamaño de página (default: 20)
- sort: campo de ordenamiento (ejemplo: "name,desc")
