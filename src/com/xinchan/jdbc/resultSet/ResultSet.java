package com.xinchan.jdbc.resultSet;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-18
 */
public class ResultSet {
    @Test
    public void ResultSetTest() throws ClassNotFoundException, SQLException, IOException {
        // 使用方式五获取数据库表的连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        // 注册驱动
        Class.forName(driver);
        // 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        // MySQL 操作
        String select = "SELECT id, name, sex, birthday FROM actor";
        Statement statement = connection.createStatement();
        java.sql.ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            String birthday = resultSet.getString(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + birthday);
        }
        // 关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
