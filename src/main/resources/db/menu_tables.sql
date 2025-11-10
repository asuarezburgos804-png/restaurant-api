-- Tabla de categorías de menú
CREATE TABLE IF NOT EXISTS menu_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Insertar categorías 
INSERT INTO menu_categories (name) VALUES
    ('Hamburguesa'),
    ('Pizza'),
    ('Bebida'),
    ('Complemento'),
    ('Postre'),
    ('Combo')
ON DUPLICATE KEY UPDATE name = VALUES(name);

-- Crear tabla de items del menú
CREATE TABLE IF NOT EXISTS menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    image_path VARCHAR(255),
    category_id BIGINT NOT NULL,
    available BOOLEAN DEFAULT true,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_menu_item_category
        FOREIGN KEY (category_id)
        REFERENCES menu_categories(id)
);

-- Crear índices
CREATE INDEX idx_menu_items_category ON menu_items(category_id);
CREATE INDEX idx_menu_items_available ON menu_items(available);
CREATE INDEX idx_menu_items_name ON menu_items(name);

-- Crear directorio para imágenes si no existe
-- Este comentario es para recordar crear el directorio en la aplicación
-- El path será: src/main/resources/static/images/menu
