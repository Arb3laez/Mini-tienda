/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.JOptionPane;
import controller.MiniStoreController;
import controller.ProductCreationController;
import util.InputValidator;


/**
 *
 * @author df
 */
        
    
public class MiniStoreGUI{

    private final MiniStoreController controller;
    private final ProductCreationController productCreationService;

    public MiniStoreGUI() {
        this.controller = new MiniStoreController();
        this.productCreationService = new ProductCreationController(controller.getInventoryService());
    }

    public void start() {
        boolean continueLoop = true;

        while (continueLoop) {
            String menu = showMainMenu();

            if (menu == null) {
                continueLoop = false;
                continue;
            }

            switch (menu) {
                case "1":
                    handleAddProduct();
                    break;

                case "2":
                    handleShowInventory();
                    break;

                case "3":
                    handleBuyProduct();
                    break;

                case "4":
                    handleShowStatistics();
                    break;

                case "5":
                    handleSearchProduct();
                    break;

                case "6":
                    handleExit();
                    continueLoop = false;
                    break;

                default:
                    showErrorMessage("Choose a valid option from the menu");
                    break;
            }
        }
    }

    // Métodos auxiliares para manejar cada opción del menú

    private String showMainMenu() {
        return JOptionPane.showInputDialog("""
                     MiniStore     

                1. Add product
                2. Show inventory
                3. Buy products
                4. Statistics
                5. Search product by name
                6. Exit

                Select an option
                """);
    }

    private void handleAddProduct() {
        try {
            String name = getInput("Input product's name");
            if (!InputValidator.isValidString(name)) {
                return;
            }

            Double price = InputValidator.parseDouble(getInput("Input product's price"));
            if (!InputValidator.isValidPrice(price)) {
                showErrorMessage("Price must be a positive number");
                return;
            }

            String category = getInput("Product's category");
            if (!InputValidator.isValidString(category)) {
                return;
            }

            String typeOfProduct = getInput("""
                    Select a type of product

                    1. Food
                    2. Appliance

                    Choose an option
                    """);

            if (!InputValidator.isValidProductTypeOption(typeOfProduct)) {
                showErrorMessage("Choose a valid option");
                return;
            }

            Integer stock = InputValidator.parseInt(getInput("Units for stock"));
            if (!InputValidator.isValidQuantity(stock)) {
                showErrorMessage("Stock must be a positive number");
                return;
            }

            ProductCreationController.ProductCreationResult result;

            if (typeOfProduct.equals("1")) {
                String expirationDate = getInput("Input " + name + "'s expiration date");
                if (!InputValidator.isValidString(expirationDate)) {
                    return;
                }
                result = productCreationService.createFoodProduct(name, price, category, stock, expirationDate);

            } else if (typeOfProduct.equals("2")) {
                Integer warrantyMonths = InputValidator.parseInt(getInput("Input " + name + "'s months of warranty"));
                if (!InputValidator.isValidWarrantyMonths(warrantyMonths)) {
                    showErrorMessage("Months of warranty must be a non-negative number");
                    return;
                }
                result = productCreationService.createApplianceProduct(name, price, category, stock, warrantyMonths);
            } else {
                showErrorMessage("Invalid product type");
                return;
            }

            showMessage(result.getMessage());

        } catch (Exception e) {
            showErrorMessage("Error adding product: " + e.getMessage());
        }
    }

    private void handleShowInventory() {
        String inventoryList = controller.showInventory();
        showMessage(inventoryList);
    }

    private void handleBuyProduct() {
        try {
            String productName = getInput("Input product's name");
            if (!InputValidator.isValidString(productName)) {
                return;
            }

            Integer quantity = InputValidator.parseInt(getInput("Enter quantity:"));
            if (!InputValidator.isValidQuantity(quantity)) {
                showErrorMessage("Quantity must be a positive number");
                return;
            }

            String result = controller.buyProduct(productName, quantity);
            showMessage(result);
        } catch (Exception e) {
            showErrorMessage("Error buying product: " + e.getMessage());
        }
    }

    private void handleShowStatistics() {
        String statistics = controller.getStatistics();
        showMessage(statistics);
    }

    private void handleSearchProduct() {
        String searchTerm = getInput("Enter product name to search:");
        if (!InputValidator.isValidString(searchTerm)) {
            showMessage("Search cancelled");
            return;
        }

        String searchResults = controller.searchProduct(searchTerm);
        showMessage(searchResults);
    }

    private void handleExit() {
        String finalTicket = controller.getFinalTicket();
        showMessage(finalTicket);
        showMessage("Thank you for using MiniStore!");
    }

    // Métodos utilitarios para la interfaz

    private String getInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
    


