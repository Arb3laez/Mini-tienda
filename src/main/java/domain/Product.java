/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author df
 */
 public abstract class Product {

    private int id;
    private String name;
    private double price;
    private String category;
    private int stock;

    // Constructor to create a product with all attributes except id
    // Used when adding a new product
    public Product(String name, double price, String category, int stock) {

        validations(name, price, category, stock);
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;

    }

    // Constructor to create a product with all attributes including id
    // Used when retrieving a product from the database
    public Product(int id, String name, double price, String category, int stock) {
        this(name, price, category, stock);
        this.id = id;
    }

    // Method to validate product attributes
    private void validations(String name, double price, String category, int stock) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name input can't be empty or with a null value.");
        }
        if (price <= 0.0 || Double.isNaN(price)) {
            throw new IllegalArgumentException("The price has to be a positive value.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("The category input cannot be null or empty.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("The stock quantity cannot be negative.");
        }
    }

    // Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name input can't be empty or with a null value.");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException("The price has to be a positive value.");
        }
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("The category input cannot be null or empty.");
        }
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("The stock quantity cannot be negative.");
        }
        this.stock = stock;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getDescription();
}

