/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author df
 */
 public class Food extends Product {

    private String expirationDate;

    // Constructor
    public Food(String name, double price, String category, int stock, String expirationDate) {
        super(name, price, category, stock);

        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("The expiration date input cannot be null or empty.");
        }

        this.expirationDate = expirationDate;
    }

    public Food(int id, String name, double price, String category, int stock, String expirationDate) {
        this(name, price, category, stock, expirationDate);
        setId(id); // Usar el setter para validar el id
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            throw new IllegalArgumentException("The expiration date input cannot be null or empty.");
        }
        this.expirationDate = expirationDate;
    }

    // Overrided methods
    @Override
    public String getDescription() {
        return "ID: " + getId() + " | " + getName() + " - $" + getPrice() + " | Category: " + getCategory()
                + " | Expiration date: " + getExpirationDate();
    }
}

