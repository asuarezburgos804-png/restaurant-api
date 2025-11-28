# Diagrama de Clases y Relaciones

## Modelos de Dominio

### User

```
+------------------+
|      User        |
+------------------+
| -id: Long        |
| -username: String|
| -password: String|
| -role: Role      |
| -active: boolean |
+------------------+
```

Relaciones:

- Uno-a-muchos con Order (como waiter) [Aún no implmento esto xd, tal vez se borre]
- Uno-a-muchos con StockHistory (como usuario que realiza operaciones)

### MenuItem

```
+-------------------+
|     MenuItem      |
+-------------------+
| -id: Long         |
| -name: String     |
| -price: BigDecimal|
| -description: Text|
| -imagePath: String|
| -category: MenuItemCategory|
| -available: boolean|
| -createdAt: DateTime|
| -updatedAt: DateTime|
+-------------------+
```

Relaciones:

- Muchos-a-uno con MenuItemCategory
- Uno-a-muchos con OrderItem
- Uno-a-muchos con SaleItem

### MenuItemCategory

```
+------------------+
| MenuItemCategory |
+------------------+
| -id: Long        |
| -name: String    |
+------------------+
```

Relaciones:

- Uno-a-muchos con MenuItem

### Order

```
+------------------+
|      Order       |
+------------------+
| -id: Long        |
| -tableNumber: Int|
| -type: OrderType |
| -status: OrderStatus|
| -customerName: String|
| -customerPhone: String|
| -total: Double   |
| -waiter: User    |
| -items: List<OrderItem>|
| -createdAt: DateTime|
| -updatedAt: DateTime|
+------------------+
```

Relaciones:

- Muchos-a-uno con User (waiter)[Aún no implmento esto xd, tal vez se borre]
- Uno-a-muchos con OrderItem

### OrderItem

```
+------------------+
|    OrderItem     |
+------------------+
| -id: Long        |
| -order: Order    |
| -item: MenuItem  |
| -quantity: Int   |
| -subtotal: Double|
| -notes: String   |
+------------------+
```

Relaciones:

- Muchos-a-uno con Order
- Muchos-a-uno con MenuItem

### Sale

```
+------------------+
|      Sale        |
+------------------+
| -id: Long        |
| -name: String    |
| -description: Text|
| -saleDate: DateTime|
| -totalPrice: BigDecimal|
| -items: List<SaleItem>|
| -createdAt: DateTime|
| -updatedAt: DateTime|
+------------------+
```

Relaciones:

- Uno-a-muchos con SaleItem

### SaleItem

```
+------------------+
|    SaleItem      |
+------------------+
| -id: Long        |
| -sale: Sale      |
| -menuItem: MenuItem|
| -priceAtTime: BigDecimal|
| -createdAt: DateTime|
+------------------+
```

Relaciones:

- Muchos-a-uno con Sale
- Muchos-a-uno con MenuItem

### InventoryItem

```
+------------------+
|  InventoryItem   |
+------------------+
| -id: Long        |
| -name: String    |
| -unitPrice: BigDecimal|
| -stock: Double   |
| -measurementUnit: MeasurementUnit|
| -createdAt: DateTime|
| -lastUpdated: DateTime|
+------------------+
```

Relaciones:

- Muchos-a-uno con MeasurementUnit
- Uno-a-muchos con StockHistory

### MeasurementUnit

```
+------------------+
| MeasurementUnit  |
+------------------+
| -id: Long        |
| -name: String    |
| -symbol: String  |
| -createdAt: DateTime|
| -updatedAt: DateTime|
+------------------+
```

Relaciones:

- Uno-a-muchos con InventoryItem

### Reservation

```
+------------------+
|   Reservation    |
+------------------+
| -id: Long        |
| -name: String    |
| -phone: String   |
| -date: Date      |
| -time: Time      |
| -guests: Int     |
| -status: String  |
| -createdAt: DateTime|
| -updatedAt: DateTime|
+------------------+
```

### StockHistory

```
+------------------+
|  StockHistory    |
+------------------+
| -id: Long        |
| -item: InventoryItem|
| -operationType: Enum|
| -amount: Double  |
| -previousStock: Double|
| -newStock: Double|
| -user: User      |
| -createdAt: DateTime|
+------------------+
```

Relaciones:

- Muchos-a-uno con InventoryItem
- Muchos-a-uno con User

## Enumeraciones

### Role

```
+------------------+
|      Role        |
+------------------+
| ADMIN            |
| CAMARERO         |
| COCINERO         |
+------------------+
```

### OrderType

```
+------------------+
|    OrderType     |
+------------------+
| DINE_IN          |
| TAKEOUT          |
| DELIVERY         |
+------------------+
```

### OrderStatus

```
+------------------+
|   OrderStatus    |
+------------------+
| PENDING          |
| IN_PROGRESS      |
| COMPLETED        |
| CANCELLED        |
+------------------+
```

## Relaciones Principales

1. Gestión de Menú

```
MenuItemCategory 1 ----* MenuItem
```

2. Gestión de Órdenes

```
User 1 ----* Order
Order 1 ----* OrderItem
MenuItem 1 ----* OrderItem
```

3. Gestión de Ventas

```
Sale 1 ----* SaleItem
MenuItem 1 ----* SaleItem
```

4. Gestión de Inventario

```
MeasurementUnit 1 ----* InventoryItem
InventoryItem 1 ----* StockHistory
User 1 ----* StockHistory
```

## Notas de Implementación

1. Todas las entidades incluyen:

   - ID autogenerado
   - Timestamps de auditoría (createdAt/updatedAt)
   - Validaciones JPA/Jakarta

2. Relaciones bidireccionales:

   - Order-OrderItem
   - Sale-SaleItem
   - InventoryItem-StockHistory

3. Lazy Loading:

   - Colecciones (List<>)
   - Relaciones @ManyToOne configurables

4. Cascada:
   - Order -> OrderItems (ALL)
   - Sale -> SaleItems (ALL)
   - InventoryItem -> StockHistory (PERSIST)
