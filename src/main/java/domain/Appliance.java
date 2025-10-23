/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author df
 */
public class Appliance extends Product {

    private int warrantyMonths;

    // Constructor
    public Appliance(String name, double price, String category, int stock, int warrantyMonths) {
        super(name, price, category, stock);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("The warranty months quantity cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
    }

    public Appliance(int id, String name, double price, String category, int stock, int warrantyMonths) {
        this(name, price, category, stock, warrantyMonths);
        setId(id); // Usar el setter para validar el id
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("The warranty months quantity cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
    }

    // Overrided methods
    @Override
    public String getDescription() {
        return "ID: " + getId() + " | " + getName() + " - $" + getPrice() + " | Category: " + getCategory()
                + " | Warranty: " + getWarrantyMonths() + " months";
    }
}
