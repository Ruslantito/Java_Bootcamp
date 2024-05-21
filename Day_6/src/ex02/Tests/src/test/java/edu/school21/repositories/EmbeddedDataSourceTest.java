package edu.school21.repositories;

import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private DataSource db;
    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("./schema.sql")
                .addScript("./data.sql")
                .build();
    }

    @Test
    public void testDataAccess() throws SQLException {       
        Connection connDB = db.getConnection();
        assertNotNull(connDB);
        connDB.close();
    }

}
