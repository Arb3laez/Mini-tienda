/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  df
 * Created: 22/10/2025
 */


-- 1. Borrar y crear la base
DROP DATABASE IF EXISTS minitienda_db;
CREATE DATABASE minitienda_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE minitienda_db;

-- 2. Crear usuario y permisos
CREATE USER IF NOT EXISTS 'minitienda_user'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON minitienda_db.* TO 'minitienda_user'@'localhost';
FLUSH PRIVILEGES;

-- 3. Crear tabla products
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_ VARCHAR(255) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    type ENUM('FOOD', 'APPLIANCE', 'UNKNOWN') NOT NULL DEFAULT 'UNKNOWN',
    expiration_date VARCHAR(50) NULL,
    warranty_months INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 4. Revisar
SHOW TABLES;products
DESCRIBE products;
