/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import domain.Product;
import java.util.List;


/**
 *
 * @author df
 */

public interface ProductService {
     /**
     * Create a new product
     * 
     * @param product the product to create
     * @return the created product
     */
    Product create(Product product);

    /**
     * Search for a product by its ID
     * 
     * @param id the ID of the product to search for
     * @return the product found, or null if not found
     */
    Product searchById(int id);

    /**
     * Search for all products
     * 
     * @return a list of all products
     */
    List<Product> searchAll();

    /**
     * Update an existing product
     * 
     * @param product the product to update
     * @return void
     */
    void update(Product product);

    /**
     * Delete a product by its ID
     * 
     * @param id the ID of the product to delete
     * @return void
     */
    void delete(int id);

    /**
     * Find a product by its exact name
     * 
     * @param name the exact name of the product to find
     * @return the product found, or null if not found
     */
    Product findByNameExact(String name);

    /**
     * Find products whose names contain the given term
     * 
     * @param term the term to search for within product names
     * @return a list of products whose names contain the term
     */
    List<Product> findByNameContains(String term);
}
