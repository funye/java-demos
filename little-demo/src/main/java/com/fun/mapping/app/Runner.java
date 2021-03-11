package com.fun.mapping.app;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException {
        ResultSet resultSet = null;

        while (resultSet.next()) {
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
        }
    }


    JdbcTemplate jdbcTemplate;

    public void test() {
        jdbcTemplate.queryForObject("", new Object[]{1L, "张三"}, City.class);
    }
}
