/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionFactory;
import domain.Appliance;
import domain.Food;
import domain.Product;
import exception.DuplicadoException;
import exception.PersistenciaException;
import service.ProductService;


/**
 *
 * @author df
 */

public class JdbcProductoRepository implements ProductService {

    @Override
    public Product create(Product product) {
        String sql = "INSERT INTO products (name_, price, category, stock, type, expiration_date, warranty_months) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCategory());
            ps.setInt(4, product.getStock());

            if (product instanceof Food) {
                ps.setString(5, "FOOD");
                ps.setString(6, ((Food) product).getExpirationDate());
                ps.setNull(7, Types.INTEGER);
            } else if (product instanceof Appliance) {
                ps.setString(5, "APPLIANCE");
                ps.setNull(6, Types.VARCHAR);
                ps.setInt(7, ((Appliance) product).getWarrantyMonths());
            } else {
                // generic product fallback
                ps.setString(5, "UNKNOWN");
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.INTEGER);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1)); // package-private setId in Product
                }
            }
            return product;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062 || (e.getSQLState() != null && e.getSQLState().startsWith("23"))) {
                throw new DuplicadoException("Producto con el mismo nombre ya existe: " + product.getName(), e);
            }
            throw new PersistenciaException("Error al crear el producto en la base de datos: " + e.getMessage(), e);
        }

    }

    @Override
    public Product searchById(int id) {
        String sql = "SELECT id, name_, price, category, stock, type, expiration_date, warranty_months FROM products WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return mapRow(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar producto por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> searchAll() {
        String sql = "SELECT id, name_, price, category, stock, type, expiration_date, warranty_months FROM products ORDER BY id";
        List<Product> res = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                res.add(mapRow(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar productos: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name_ = ?, price = ?, category = ?, stock = ?, type = ?, expiration_date = ?, warranty_months = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCategory());
            ps.setInt(4, product.getStock());
            if (product instanceof Food) {
                ps.setString(5, "FOOD");
                ps.setString(6, ((Food) product).getExpirationDate());
                ps.setNull(7, Types.INTEGER);
            } else if (product instanceof Appliance) {
                ps.setString(5, "APPLIANCE");
                ps.setNull(6, Types.VARCHAR);
                ps.setInt(7, ((Appliance) product).getWarrantyMonths());
            } else {
                ps.setString(5, "UNKNOWN");
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.INTEGER);
            }
            ps.setInt(8, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062 || (e.getSQLState() != null && e.getSQLState().startsWith("23"))) {
                throw new DuplicadoException("Producto con el mismo nombre ya existe: " + product.getName(), e);
            }
            throw new PersistenciaException("Error al actualizar el producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar el producto: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findByNameExact(String name) {
        String sql = "SELECT id, name_, price, category, stock, type, expiration_date, warranty_months FROM products WHERE LOWER(name_) = LOWER(?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return mapRow(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar producto por nombre: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> findByNameContains(String term) {
        String sql = "SELECT id, name_, price, category, stock, type, expiration_date, warranty_months FROM products WHERE LOWER(name_) LIKE LOWER(?) ORDER BY id";
        List<Product> res = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + term.trim() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    res.add(mapRow(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar productos por término: " + e.getMessage(), e);
        }
    }

    // Mapear fila a Food o Appliance
    private Product mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name_");
        double price = rs.getDouble("price");
        String category = rs.getString("category");
        int stock = rs.getInt("stock");
        String type = rs.getString("type");
        String expirationDate = rs.getString("expiration_date");
        int warrantyMonths = rs.getInt("warranty_months"); // si es NULL devuelve 0, revisar wasNull

        if (type == null)
            type = "UNKNOWN";

        if ("FOOD".equalsIgnoreCase(type)) {
            // expirationDate assumed not null; validations in constructor will handle
            // invalid values
            return new Food(id, name, price, category, stock, expirationDate);
        } else if ("APPLIANCE".equalsIgnoreCase(type)) {
            // warrantyMonths may be 0 if null; check wasNull
            int warranty = warrantyMonths;
            if (rs.wasNull())
                warranty = 0;
            return new Appliance(id, name, price, category, stock, warranty);
        } else {
            // Si por alguna razón no hay tipo, devolver Food-like básico con expiration
            // null
            return new Food(id, name, price, category, stock, expirationDate == null ? "" : expirationDate) {
                @Override
                public String getDescription() {
                    return "ID: " + id + " | " + name + " - $" + price + " | Category: " + category;
                }
            };
        }
    }
}

