/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package controller;

import domain.Appliance;
import domain.Food;
import exception.DatoInvalidoException;
import exception.DuplicadoException;
import exception.PersistenciaException;
import service.InventoryService;
import util.InputValidator;

/**
 * Servicio que maneja la creación de productos
 * Encapsula la lógica de creación de objetos Product y sus validaciones
 */
public class ProductCreationController {

    private final InventoryService inventoryService;

    public ProductCreationController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Crea un producto Food
     * 
     * @param name           Nombre del producto
     * @param price          Precio del producto
     * @param category       Categoría del producto
     * @param stock          Cantidad en stock
     * @param expirationDate Fecha de expiración
     * @return Resultado de la operación (éxito o error)
     */
    public ProductCreationResult createFoodProduct(String name, Double price, String category,
            Integer stock, String expirationDate) {
        try {
            // Validar datos básicos
            if (!InputValidator.isValidString(name)) {
                throw new DatoInvalidoException("El nombre del producto no puede estar vacío");
            }

            if (!InputValidator.isValidPrice(price)) {
                throw new DatoInvalidoException("El precio debe ser un número positivo");
            }

            if (!InputValidator.isValidString(category)) {
                throw new DatoInvalidoException("La categoría no puede estar vacía");
            }

            if (!InputValidator.isValidQuantity(stock)) {
                throw new DatoInvalidoException("El stock debe ser un número positivo");
            }

            if (!InputValidator.isValidString(expirationDate)) {
                throw new DatoInvalidoException("La fecha de expiración es requerida para productos alimenticios");
            }

            Food food = new Food(name, price, category, stock, expirationDate);
            boolean success = inventoryService.addProduct(food, stock);

            if (success) {
                return ProductCreationResult.success("Producto alimenticio agregado exitosamente!");
            } else {
                return ProductCreationResult.error("El producto ya existe en el inventario");
            }

        } catch (DatoInvalidoException e) {
            return ProductCreationResult.error("Error de validación: " + e.getMessage());
        } catch (DuplicadoException e) {
            return ProductCreationResult.error("Error de duplicado: " + e.getMessage());
        } catch (PersistenciaException e) {
            return ProductCreationResult.error("Error de persistencia: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ProductCreationResult.error("Error de validación: " + e.getMessage());
        }
    }

    /**
     * Crea un producto Appliance
     * 
     * @param name           Nombre del producto
     * @param price          Precio del producto
     * @param category       Categoría del producto
     * @param stock          Cantidad en stock
     * @param warrantyMonths Meses de garantía
     * @return Resultado de la operación (éxito o error)
     */
    public ProductCreationResult createApplianceProduct(String name, Double price, String category,
            Integer stock, Integer warrantyMonths) {
        try {
            // Validar datos básicos
            if (!InputValidator.isValidString(name)) {
                throw new DatoInvalidoException("El nombre del producto no puede estar vacío");
            }

            if (!InputValidator.isValidPrice(price)) {
                throw new DatoInvalidoException("El precio debe ser un número positivo");
            }

            if (!InputValidator.isValidString(category)) {
                throw new DatoInvalidoException("La categoría no puede estar vacía");
            }

            if (!InputValidator.isValidQuantity(stock)) {
                throw new DatoInvalidoException("El stock debe ser un número positivo");
            }

            if (!InputValidator.isValidWarrantyMonths(warrantyMonths)) {
                throw new DatoInvalidoException("Los meses de garantía deben ser un número no negativo");
            }

            Appliance appliance = new Appliance(name, price, category, stock, warrantyMonths);
            boolean success = inventoryService.addProduct(appliance, stock);

            if (success) {
                return ProductCreationResult.success("Producto electrodoméstico agregado exitosamente!");
            } else {
                return ProductCreationResult.error("El producto ya existe en el inventario");
            }

        } catch (DatoInvalidoException e) {
            return ProductCreationResult.error("Error de validación: " + e.getMessage());
        } catch (DuplicadoException e) {
            return ProductCreationResult.error("Error de duplicado: " + e.getMessage());
        } catch (PersistenciaException e) {
            return ProductCreationResult.error("Error de persistencia: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ProductCreationResult.error("Error de validación: " + e.getMessage());
        }
    }

    /**
     * Clase interna para representar el resultado de la creación
     */
    public static class ProductCreationResult {
        private final boolean success;
        private final String message;

        private ProductCreationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public static ProductCreationResult success(String message) {
            return new ProductCreationResult(true, message);
        }

        public static ProductCreationResult error(String message) {
            return new ProductCreationResult(false, message);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}


