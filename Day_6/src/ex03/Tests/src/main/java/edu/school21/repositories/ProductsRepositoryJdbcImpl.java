package edu.school21.repositories;

import java.sql.*;
import edu.school21.models.*;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.sql.SQLException;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource db;
    public ProductsRepositoryJdbcImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productResult = new ArrayList<>();
        String query = "select * from product";
        try(Connection connDB = db.getConnection(); 
            Statement stm = connDB.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            while(res.next()) {
                long id = res.getLong(1);
                String name = res.getString(2);
                double price = res.getDouble(3);
                Product productTmp = new Product(id, name, price);
                productResult.add(productTmp);
            }
        }
        return productResult;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Optional<Product> result = Optional.empty();
        String query = "select * from product where id = " + id;
        try(Connection connDB = db.getConnection(); 
            Statement stm = connDB.createStatement();
            ResultSet res = stm.executeQuery(query)) {
            if(res.next()) {
                long productId = res.getLong(1);
                String name = res.getString(2);
                double price = res.getDouble(3);
                Product productTmp = new Product(productId, name, price);
                result = Optional.of(productTmp);
            }
        }
        return result;
    }

    @Override
    public void update(Product product) throws SQLException {
        CheckProductData(product);
        String query = "update product set ";
        query += "name = '" + product.GetName() + "', ";
        query += "price = " + product.GetPrice() + " ";
        query += "where id = " + product.GetId();
        try(Connection connDB = db.getConnection(); 
            Statement stm = connDB.createStatement()) {
            int res = stm.executeUpdate(query);
            if (res > 0) {
                System.out.println("The records were updated: " + res);
            } else {
                System.out.println("Not any records were updated!!");
            }
        }
    }

    @Override
    public void save(Product product) throws SQLException {
        CheckProductData(product);
        String query = "insert into product (id, name, price) values (";
        query += product.GetId() + ", ";
        query += "'" + product.GetName() + "', ";
        query += product.GetPrice() + ")";
        try(Connection connDB = db.getConnection(); 
            Statement stm = connDB.createStatement()) {
            int res = stm.executeUpdate(query);
            if (res > 0) {
                System.out.println("The records were saved: " + res);
            } else {
                System.out.println("Not any records were saved!!");
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "delete from product where id = " + id;
        try(Connection connDB = db.getConnection(); 
            Statement stm = connDB.createStatement()) {
            int res = stm.executeUpdate(query);
            if (res == 0) {
                throw new SQLException("No records found");
            } else {
                System.out.println("Record successfully deleted (id=" + id + ")");
            }
        }
    }

    private void CheckProductData(Product product) {
        if(product.GetName() == null) {
            throw new IllegalArgumentException("WARNING!!! Wrong Name.");
        }
        if(product.GetPrice() < 0) {
            throw new IllegalArgumentException("WARNING!!! Wrong Price.");
        }
    }

}
