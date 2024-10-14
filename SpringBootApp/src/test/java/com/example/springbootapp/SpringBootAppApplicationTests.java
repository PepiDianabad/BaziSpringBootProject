package com.example.springbootapp;

import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



class SpringBootAppApplicationTests {

    @Test
    void test1() {
        PGConnectionPoolDataSource p  = new PGConnectionPoolDataSource();
        p.setPassword("secret");
        p.setUser("myuser");
        p.setDatabaseName("mydatabase");
        p.setServerNames(new String[]{"127.0.0.1"});
        p.setPortNumbers(new int[]{5432});

        try (Connection c = p.getConnection()) {
            String query = "SELECT * FROM country";
            try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("aaaa");
                    String iso2country_code = rs.getString(1);
                    String iso3country_code = rs.getString(2);
                    String name = rs.getString(3);
                    System.out.printf(" iso2country_code: %s, iso3country_code: %s, Location: %s%n", iso2country_code, iso3country_code, name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test2() {
        PGConnectionPoolDataSource p  = new PGConnectionPoolDataSource();
        p.setPassword("secret");
        p.setUser("myuser");
        p.setDatabaseName("mydatabase");
        p.setServerNames(new String[]{"127.0.0.1"});
        p.setPortNumbers(new int[]{5432});

        try (Connection c = p.getConnection()) {
            c.setAutoCommit(true);
            String query = "INSERT INTO country (iso3country_code, iso2country_code, name) VALUES ('kur123', '3kura1243', 'Makedoniq444');\n ";
            try (Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            }
        } catch (SQLException e) {
            //p.setDefaultAutoCommit(true);
            throw new RuntimeException(e);
        }
    }
}
