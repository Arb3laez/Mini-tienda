    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
     */
    package service;
    import domain.Product;

    /**
     *
     * @author df
     */
    public interface InventoryService {
        /**
         * Adds a product to the inventory with the specified initial stock.
         * 
         * @param product      the product to be added
         * @param initialStock the initial stock quantity for the product
         * @return
         */
        boolean addProduct(Product product, int initialStock);

        /**
         * Displays the current inventory of products.
         * 
         * @return A string representation of the inventory.
         */
        String showInventory();

        /**
         * Searches for a product by its name.
         * 
         * @param productName the name of the product to search for
         * @return
         */
        String searchProduct(String productName);

        /**
         * Retrieves a product by its name.
         * 
         * @param productName the name of the product to retrieve
         * @return
         */
        Product getProductByName(String productName);

        /**
         * Processes the purchase of a specified quantity of a product.
         * 
         * @param productName      the name of the product to purchase
         * @param requiredQuantity the quantity of the product to purchase
         * @return
         */
        String buyProduct(String productName, int requiredQuantity);

        /**
         * Generates statistics about the inventory.
         * 
         * @return A string representation of the inventory statistics.
         */
        String getStatistics();

        /**
         * Generates the final ticket for the purchases made.
         * 
         * @return A string representation of the final ticket.
         */
        String getFinalTicket();
    }



