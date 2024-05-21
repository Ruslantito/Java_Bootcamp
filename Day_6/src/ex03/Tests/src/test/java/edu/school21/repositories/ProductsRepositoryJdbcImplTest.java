package edu.school21.repositories;

import edu.school21.models.Product;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<Product>() {{
        add(new Product(1L, "first flight", 500.99D));
        add(new Product(2L, "first food", 35.99D));
        add(new Product(3L, "first car", 3500.99D));
        add(new Product(4L, "second food", 53.99D));
        add(new Product(5L, "second car", 5300.99D));
    }};
    final Product EXPECTED_FIND_BY_ID       = new Product(2L, "first food", 35.99D);
    final Product EXPECTED_UPDATE_PRODUCT   = new Product(4L, "third food!!!!", 77.77D);
    final Product EXPECTED_SAVED_PRODUCT    = new Product(6L, "helicopter", 500150D);

    ProductsRepositoryJdbcImpl prodRepos;

    private DataSource db;
    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("./schema.sql")
                .addScript("./data.sql")
                .build();

        prodRepos = new ProductsRepositoryJdbcImpl(db);
    }
    
    @Test
    public void testDataAccess() throws SQLException {       
        Connection connDB = db.getConnection();
        assertNotNull(connDB);
        connDB.close();
    }

    @Test
    void testFindAllGood() throws SQLException {
        List<Product> actual = prodRepos.findAll();
        assertArrayEquals(EXPECTED_FIND_ALL_PRODUCTS.toArray(), actual.toArray());
    }
    
    @Test
    void testFindByIdGood() throws SQLException {
        Product actual = prodRepos.findById(2L).get();
        assertEquals(EXPECTED_FIND_BY_ID, actual);
    }
    @Test
    void testFindByIdBad() throws SQLException {
        Optional<Product> actual = prodRepos.findById(12L);
        assertFalse(actual.isPresent());
    }

    @Test
    void testUpdateGood() throws SQLException {
        prodRepos.update(new Product(4L, "third food!!!!", 77.77D));
        Product actual = prodRepos.findById(4L).get();
        assertEquals(EXPECTED_UPDATE_PRODUCT, actual);
    }
    @Test
    void testUpdateBad() {
        assertThrows(IllegalArgumentException.class, ()->{
                prodRepos.update(new Product(4L, null, 77.77D));
            }    
        );
    }
    @Test
    void testUpdateBad2() {
        assertThrows(IllegalArgumentException.class, ()->{
                prodRepos.update(new Product(4L, "third food!!!!", -77.77D));
            }    
        );
    }
    
    @Test
    void testSaveGood() throws SQLException {
        prodRepos.save(new Product(6L, "helicopter", 500150D));
        Product actual = prodRepos.findById(6L).get();
        assertEquals(EXPECTED_SAVED_PRODUCT, actual);
    }
    @Test
    void testSaveBad() {
        assertThrows(IllegalArgumentException.class, ()->{
                prodRepos.save(new Product(6L, null, 500150D));
            }    
        );
    }
    @Test
    void testSaveBad2() {
        assertThrows(IllegalArgumentException.class, ()->{
                prodRepos.save(new Product(6L, "helicopter", -500150D));
            }    
        );
    }
    
    @Test
    void testDeleteGood() throws SQLException {
        prodRepos.delete(5L);
        Optional<Product> actual = prodRepos.findById(5L);
        assertFalse(actual.isPresent());
    }
    @Test
    void testDeleteBad() {
        assertThrows(SQLException.class, ()->{
                prodRepos.delete(17L);
            }    
        );
    }

}
