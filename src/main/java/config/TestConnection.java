/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import domain.Appliance;
import domain.Food;
import domain.Product;
import repository.JdbcProductoRepository;



/**
 *
 * @author df
 */
public class TestConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Probando conexión a la base de datos ");

            // Probar conexión
            var connection = ConnectionFactory.getConnection();
            System.out.println("✓ Conexión exitosa a la base de datos");
            connection.close();

            // Probar repositorio
            System.out.println("\n=== Probando repositorio JDBC ===");
            JdbcProductoRepository repo = new JdbcProductoRepository();

            // Crear un producto de prueba
            Food testFood = new Food("Naranja", 1.50, "Frutas", 10, "2024-12-31");
            System.out.println("Creando producto de prueba: " + testFood.getName());

            Product createdFood = repo.create(testFood);
            System.out.println("✓ Producto creado con ID: " + createdFood.getId());

            Appliance testAppliance = new Appliance("Lavarropas", 250.0, "Electrodomésticos", 5, 12);
            System.out.println("Creando producto de prueba: " + testAppliance.getName());

            Product createdAppliance = repo.create(testAppliance);
            System.out.println("✓ Producto creado con ID: " + createdAppliance.getId());

            // Buscar el producto
            Product foundFood = repo.searchById(createdFood.getId());
            if (foundFood != null) {
                System.out.println("✓ Producto encontrado: " + foundFood.getDescription());
            } else {
                System.out.println("✗ Error: No se pudo encontrar el producto");
            }

            Product foundAppliance = repo.searchById(createdAppliance.getId());
            if (foundAppliance != null) {
                System.out.println("✓ Producto encontrado: " + foundAppliance.getDescription());
            } else {
                System.out.println("✗ Error: No se pudo encontrar el producto");
            }

            // Listar todos los productos
            var allProducts = repo.searchAll();
            System.out.println("✓ Total de productos en la base de datos: " + allProducts.size());

            System.out.println("\n=== Prueba completada exitosamente ===");

        } catch (Exception e) {
            System.err.println("✗ Error durante la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
