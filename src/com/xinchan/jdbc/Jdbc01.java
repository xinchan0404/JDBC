package com.xinchan.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-17
 */
public class Jdbc01 {
    public static void main(String[] args) throws SQLException {
//        1. 注册驱动——加载 Driver 类；
        Driver driver = new Driver();

//        2. 获取链接——得到 Connection；需要生成 URL 和 Properties
        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        Connection connect = driver.connect(url, properties);

//        3. 执行增删改查——发送 SQL 给 MySQL 执行；
        String insert = "INSERT INTO actor VALUES(1, 'tom', 'male', '1997-04-04', '17702732900')";
        String update = "UPDATE actor SET `name` = 'jack'";
        String delete = "DELETE FROM actor WHERE id = 1";
        Statement statement = connect.createStatement();
//        int rows = statement.executeUpdate(insert);
//        int rows = statement.executeUpdate(update);
        int rows = statement.executeUpdate(delete);
        System.out.println(rows > 0 ? "成功" : "失败");

//        4. 释放资源——关闭相关链接。
        statement.close();
        connect.close();
    }
}
