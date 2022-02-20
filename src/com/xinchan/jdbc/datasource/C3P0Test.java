package com.xinchan.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 演示两种常用的数据库连接池技术：C3P0、Druid
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class C3P0Test {
    @Test
    public void C3P0Test01() throws IOException, PropertyVetoException, SQLException {
        // 1. 创建数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 2. 通过配置文件获取相关认证信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        // 3. 给数据源 comboPooledDataSource 设置相关的参数
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        // 4. 设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        // 5. 设置最大连接数
        comboPooledDataSource.setMaxPoolSize(50);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("C3P0 耗时：" + (end - start) + " ms");  // 331 ms
    }

    @Test
    public void C3P0Test02() throws SQLException {
        // 1. 创建数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("xinchan");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            // 2. 获取连接
            Connection connection = comboPooledDataSource.getConnection();
            // 3. 关闭连接
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("C3P0 耗时~~：" + (end - start));  // c3p0:  1582 ms
    }
}
