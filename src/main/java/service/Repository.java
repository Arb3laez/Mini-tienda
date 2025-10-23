/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author df
 */
  

public interface Repository<T> {

    /**
     * Creates a new item in the repository.
     * 
     * @param item The item to be created.
     */
    void create(T item);

    /**
     * Searches for an item by its ID.
     * 
     * @param id The ID of the item to search for.
     * @return The item if found, otherwise null.
     */
    T searchById(int id);

    /**
     * Searches for all items in the repository.
     * 
     * @return A list of all items.
     */
    List<T> searchAll();

    /**
     * Updates an existing item in the repository.
     * 
     * @param item The item with updated information.
     */
    void update(T item);

    /**
     * Deletes an item from the repository by its ID.
     * 
     * @param id The ID of the item to be deleted.
     */
    void delete(int id);
}

    

