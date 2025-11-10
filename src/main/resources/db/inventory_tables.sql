-- Tabla de unidades de medida
CREATE TABLE IF NOT EXISTS measurement_units (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    symbol VARCHAR(10) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de items de inventario
CREATE TABLE IF NOT EXISTS inventory_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    unit_price DECIMAL(10,2) NOT NULL,
    stock DOUBLE NOT NULL,
    measurement_unit_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (measurement_unit_id) REFERENCES measurement_units(id)
);

-- Tabla de historial de stock
CREATE TABLE IF NOT EXISTS stock_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_item_id BIGINT NOT NULL,
    operation_type ENUM('INCREASE', 'DECREASE') NOT NULL,
    amount DOUBLE NOT NULL,
    previous_stock DOUBLE NOT NULL,
    new_stock DOUBLE NOT NULL,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (inventory_item_id) REFERENCES inventory_items(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_inventory_items_stock ON inventory_items(stock);
CREATE INDEX idx_inventory_items_name ON inventory_items(name);
CREATE INDEX idx_stock_history_item_date ON stock_history(inventory_item_id, created_at);

-- Datos iniciales para unidades de medida
INSERT IGNORE INTO measurement_units (name, symbol) VALUES
('Kilogramos', 'kg'),
('Gramos', 'g'),
('Litros', 'L'),
('Mililitros', 'ml'),
('Unidades', 'u'),
('Docenas', 'doc'),
('Cajas', 'caja'),
('Paquetes', 'paq');
