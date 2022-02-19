package com.xinchan.jdbc.batch;

import com.xinchan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class BatchTest {
    @Test
    public void withoutBatchTest() throws SQLException {
        // 1. 注册驱动并获取连接
        Connection connection = JDBCUtils.getConnection();
        // 2. 获取 PreparedStatement 并执行 SQL 语句
        String insert = insert = "insert into admin_batch values(null , ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
        // 3. 释放资源
        JDBCUtils.close(null, connection, preparedStatement);
    }

    @Test
    public void withBatchTest() throws SQLException {
        // 1. 注册驱动并获取连接
        Connection connection = JDBCUtils.getConnection();
        // 2. 获取 PreparedStatement 并执行 SQL 语句
        String insert = "insert into admin_batch values(null , ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.addBatch();  // 添加需要批处理的 SQL 语句或参数
            if ((i + 1) % 1000 == 0) {
                preparedStatement.executeBatch();  // 执行批处理的 SQL 语句
                preparedStatement.clearBatch();  // 清空批处理包的语句
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
        // 3. 释放资源
        JDBCUtils.close(null, connection, preparedStatement);
    }
}
