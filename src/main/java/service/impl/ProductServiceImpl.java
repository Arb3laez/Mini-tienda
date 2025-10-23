
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;
import java.util.List;
import domain.Product;
import java.util.ArrayList;
import service.ProductService;


/**
 *
 * @author df
 */

public class ProductServiceImpl implements ProductService {

    private final List<Product> items = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Product create(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product can't be null");
        product.setId(nextId++);
        items.add(product);
        return product;
    }

    @Override
    public Product searchById(int id) {
        for (Product p : items) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public List<Product> searchAll() {
        return new ArrayList<>(items);
    }

    @Override
    public void update(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product can't be null");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == product.getId()) {
                items.set(i, product);
                return;
            }
        }
        throw new IllegalArgumentException("Product with id " + product.getId() + " not found");
    }

    @Override
    public void delete(int id) {
        items.removeIf(p -> p.getId() == id);
    }

    @Override
    public Product findByNameExact(String name) {
        if (name == null)
            return null;
        String normalized = name.trim().toLowerCase();
        for (Product p : items) {
            if (p.getName().toLowerCase().equals(normalized))
                return p;
        }
        return null;
    }

    @Override
    public List<Product> findByNameContains(String term) {
        List<Product> res = new ArrayList<>();
        if (term == null)
            return res;
        String normalized = term.trim().toLowerCase();
        for (Product p : items) {
            if (p.getName().toLowerCase().contains(normalized))
                res.add(p);
        }
        return res;
    }
}

