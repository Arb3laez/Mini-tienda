/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import repository.JdbcProductoRepository;
import service.InventoryService;
import service.ProductService;
import service.impl.InventoryServiceImpl;

/**
 * Controlador principal que maneja la lógica de la aplicación 
 * Sigue el patrón MVC separando la lógica de negocio de la vista
 */

/**
 *
 * @author df
 */

    public class MiniStoreController {

    private final InventoryService inventoryService;

    public MiniStoreController() {
        // Inyección de dependencias - fácil de cambiar para testing
        ProductService productService = new JdbcProductoRepository();
        
        this.inventoryService = new InventoryServiceImpl(productService);
    }

    // Constructor para inyección de dependencias (testing)
    public MiniStoreController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Métodos que encapsulan la lógica de negocio
    public String showInventory() {
        return inventoryService.showInventory();
    }

    public String getStatistics() {
        return inventoryService.getStatistics();
    }

    public String searchProduct(String searchTerm) {
        return inventoryService.searchProduct(searchTerm);
    }

    public String buyProduct(String productName, int quantity) {
        return inventoryService.buyProduct(productName, quantity);
    }

    public String getFinalTicket() {
        return inventoryService.getFinalTicket();
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }
}

    

