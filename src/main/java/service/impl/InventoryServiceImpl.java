/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;
import domain.Product;
import service.InventoryService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author df
 */
public class InventoryServiceImpl implements InventoryService {

    // InventoryServiceImpl inventoryService = new InventoryServiceImpl(new
    // JdbcProductRepository());

    private final ProductService repo;
    private double totalPurchases = 0.0;
    private ArrayList<String> purchaseHistory = new ArrayList<>();

    // Inyección por defecto: InMemory. Más adelante puedes inyectar JDBC.
    public InventoryServiceImpl() {
        this(new ProductServiceImpl());
    }

    // Constructor para inyección (útil en pruebas o para cambiar la implementación)
    public InventoryServiceImpl(ProductService repo) {
        this.repo = repo;
    }

    @Override
    public boolean addProduct(Product product, int initialStock) {
        if (product == null) {
            throw new IllegalArgumentException("Can't add the product without its information");
        }
        if (initialStock <= 0) {
            throw new IllegalArgumentException("You need at least one unit to put in the stock");
        }

        // comprobar duplicado por nombre exacto
        if (repo.findByNameExact(product.getName()) != null) {
            return false; // ya existe
        }

        // asignar stock y crear en repo
        product.setStock(initialStock);

        try {
            repo.create(product);
            return true;
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("DUPLICATE")) {
                return false; // indica al UI "Product already exists"
            }
            throw e; // re-lanzar otros errores
        }
    }

    @Override
    public String showInventory() {
        StringBuilder sb = new StringBuilder("    INVENTORi \n\n");
        List<Product> products = repo.searchAll();

        if (products.isEmpty()) {
            return "There are no products in the inventory";
        }

        for (Product product : products) {
            int stockUnits = product.getStock();
            String description = product.getDescription();
            sb.append(description).append(" | Stock: ").append(stockUnits).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String searchProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field's value can't be empty or null");
        }
        List<Product> results = repo.findByNameContains(productName);
        if (results.isEmpty())
            return "Product not found in the inventory";

        StringBuilder sb = new StringBuilder("  SEARCH RESULTS      \n\n");
        for (Product p : results) {
            sb.append(p.getDescription()).append(" | Stock: ").append(p.getStock()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Product getProductByName(String productName) {
        return repo.findByNameExact(productName);
    }

    @Override
    public String buyProduct(String productName, int requiredQuantity) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name field's value can't be empty or null");
        }
        if (requiredQuantity <= 0) {
            throw new IllegalArgumentException("Quantity field's value has to be a positive");
        }

        Product productFound = getProductByName(productName);

        if (productFound == null) {
            return "Product not found in the inventory";
        }

        int stockUnits = productFound.getStock();

        if (requiredQuantity > stockUnits) {
            return "Not enough units to make the transaction \nAvailable stock: " + stockUnits;
        }

        productFound.setStock(stockUnits - requiredQuantity);
        repo.update(productFound);

        double subtotal = requiredQuantity * productFound.getPrice();
        totalPurchases += subtotal;
        purchaseHistory.add(productFound.getName() + " x" + requiredQuantity + " = $" + subtotal);

        StringBuilder ticket = new StringBuilder("      TICKET PURCHASE      \n\n");
        ticket.append("Product: ").append(productFound.getName()).append("\n");
        ticket.append("Price: $").append(productFound.getPrice()).append("\n");
        ticket.append("Quantity: ").append(requiredQuantity).append("\n");
        ticket.append("Subtotal: ").append(subtotal).append("\n");
        ticket.append("Remaining stock: ").append(productFound.getStock()).append("\n");

        return ticket.toString();
    }

    @Override
    public String getStatistics() {
        List<Product> products = repo.searchAll();
        if (products.isEmpty()) {
            return "No products in the inventory";
        }
        Product mostExpensive = products.get(0);
        Product cheapest = products.get(0);

        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        StringBuilder stats = new StringBuilder("   STATISTICS      \n\n");
        stats.append("Most Expensive:\n  - ")
                .append(mostExpensive.getName())
                .append(": $").append(mostExpensive.getPrice())
                .append("\n\n");
        stats.append("Cheapest:\n  - ")
                .append(cheapest.getName())
                .append(": $").append(cheapest.getPrice())
                .append("\n");

        return stats.toString();
    }

    @Override
    public String getFinalTicket() {
        if (purchaseHistory.isEmpty()) {
            return "No purchases made";
        }
        StringBuilder ticket = new StringBuilder(" FINAL TICKET \n\n");
        for (String purchase : purchaseHistory) {
            ticket.append(purchase).append("\n");
        }
        ticket.append("\n-\n");
        ticket.append("T0TAL: $").append(totalPurchases);
        return ticket.toString();
    }
}
