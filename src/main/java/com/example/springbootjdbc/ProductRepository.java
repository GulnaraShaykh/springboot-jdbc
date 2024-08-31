package com.example.springbootjdbc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String query;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.query = read("fetch_product.sql");
    }

    public String getProductName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        try {
            return jdbcTemplate.queryForObject(query, params, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}