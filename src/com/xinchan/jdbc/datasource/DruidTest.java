package com.xinchan.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class DruidTest {
    @Test
    public void druidTest() throws Exception {
        // 1. 读取 .properties 数据
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));
        // 2. 创建 druid 数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            // 3. 开启连接
            Connection connection = dataSource.getConnection();
            // 4. 关闭连接
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid 连接池耗时：" + (end - start));  // 378 ms
    }
}
