package com.xinchan.jdbc;

import com.mysql.jdbc.Driver;
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
 * Java 程序获取数据库连接的 5 种方式
 * @author xinchan
 * @version 1.0.1 2022-02-17
 */
public class JdbcConnect {
    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();  // 驱动程序相关对应于 MySQL 提供的外部 jar 包

        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        Connection connect = driver.connect(url, properties);

        String insert = "INSERT INTO actor VALUES(1, 'tom', 'male', '1997-04-04', '177702732900')";
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(insert);
        System.out.println(rows > 0 ? "成功" : "失败");

        statement.close();
        connect.close();
    }

    @Test
    public void connect02() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");  // 动态加载，更灵活
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        Connection connection = driver.connect(url, properties);

        String insert = "INSERT INTO actor VALUES(2, 'jack', 'male', '1996-04-04', '1000')";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(insert);
        System.out.println(rows > 0 ? "成功" : "失败");

        statement.close();
        connection.close();
    }

    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        String user = "root";
        String password = "root";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        String insert = "INSERT INTO actor VALUES(3, 'rose', 'male', '1995-04-04', '3000')";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(insert);
        System.out.println(rows > 0 ? "成功" : "失败");

        statement.close();
        connection.close();
    }

    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        String user = "root";
        String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        String insert = "INSERT INTO actor VALUES(4, 'smith', 'male', '1994-04-04', '4000')";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(insert);
        System.out.println(rows > 0 ? "成功" : "失败");

        statement.close();
        connection.close();
    }

    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        String insert = "INSERT INTO actor VALUES(5, 'bob', 'male', '1993-04-04', '5000')";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(insert);
        System.out.println(rows > 0 ? "成功" : "失败");

        statement.close();
        connection.close();
    }

    @Test
    public void exercise() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        String insert1 = "INSERT INTO actor VALUES(5, 'e', 'male', '1995-04-04', '5000')";
        String insert2 = "INSERT INTO actor VALUES(4, 'd', 'male', '1994-04-04', '4000')";
        String insert3 = "INSERT INTO actor VALUES(3, 'c', 'male', '1993-04-04', '3000')";
        String insert4 = "INSERT INTO actor VALUES(2, 'b', 'male', '1992-04-04', '2000')";
        String insert5 = "INSERT INTO actor VALUES(1, 'a', 'male', '1991-04-04', '1000')";
        String update = "UPDATE actor SET `name` = 'zhengxin' WHERE id = 1";
        String delete = "DELETE FROM actor WHERE id = 3";
        Statement statement = connection.createStatement();
        int rows = 0;
        rows += statement.executeUpdate(insert1);
        rows += statement.executeUpdate(insert2);
        rows += statement.executeUpdate(insert3);
        rows += statement.executeUpdate(insert4);
        rows += statement.executeUpdate(insert5);
        rows += statement.executeUpdate(update);
        rows += statement.executeUpdate(delete);
        System.out.println(rows == 7 ? "成功" : "失败");
    }
}
