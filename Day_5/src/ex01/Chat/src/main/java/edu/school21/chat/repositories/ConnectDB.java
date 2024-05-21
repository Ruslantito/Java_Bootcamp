package edu.school21.chat.repositories;
    
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.*;

public class ConnectDB {
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String name = "changsky";
    private String password = "";
    private HikariDataSource db;

    public ConnectDB() {
        db = new HikariDataSource();
        db.setJdbcUrl(url);
        db.setUsername(name);
        db.setPassword(password);
    }

    public Connection getConnection() throws SQLException {
        return db.getConnection();
    }
}
