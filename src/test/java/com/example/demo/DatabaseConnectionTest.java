package com.example.demo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * packageName    : com.example.demo
 * fileName       : DatabaseConnectionTest
 * author         : JAEIK
 * date           : 10/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/16/24        JAEIK       최초 생성
 */
public class DatabaseConnectionTest {
    @Test
    public void testDatabaseConnection() {
        String url = "jdbc:postgresql://54.181.1.100:5432/auth?sslmode=require";
        String username = "kimjaeik";
        String password = "1815";

        // 예외 처리
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            assertNotNull(connection, "Connection should not be null");
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}
