package com.xinchan.jdbc.preparedStatement;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 演示 PreparedStatement 执行 SQL 语句
 * @author xinchan
 * @version 1.0.1 2022-02-18
 */
public class PreparedStatement {
    @Test
    public void PreparedStatementTest() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        // 注册驱动
        Class.forName(driver);
        // 创建连接
        Connection connection = DriverManager.getConnection(url, user, password);
        // 执行 SQL 语句
        String select = "SELECT name, password FROM admin WHERE name = ? AND password = ?";
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, "tom");
        preparedStatement.setString(2, "123");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        resultSet.close();
        connection.close();
        preparedStatement.close();
    }

    @Test
    public void PreparedStatementTest01() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        // 注册驱动
        Class.forName(driver);
        // 创建连接
        Connection connection = DriverManager.getConnection(url, user, password);
        // 执行 SQL 语句
        String update = "UPDATE admin SET password = ? WHERE name = ?";
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, "tom");
        preparedStatement.setString(2, "tom");
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        connection.close();
        preparedStatement.close();
    }

    @Test
    public void homework() throws ClassNotFoundException, IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        // 注册驱动
        Class.forName(driver);
        // 创建连接
        Connection connection = DriverManager.getConnection(url, user, password);
        // SQL 语句
//        String insert = "insert into admin_hw values(?, ?)";
//        String update = "update admin_hw set name = ? where name = ?";
//        String delete = "delete from admin_hw where name = ?";
        String select = "select * from admin_hw";
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(select);
//        preparedStatement.setString(1, "jack");
//        preparedStatement.setString(2, "tom");
//        int i = preparedStatement.executeUpdate();
//        if (i > 0) {
//            System.out.println("success");
//        } else {
//            System.out.println("fail");
//        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String passwd = resultSet.getString(2);
            System.out.println(name + "\t" + passwd);
        }

        resultSet.close();
        connection.close();
        preparedStatement.close();
    }
}
