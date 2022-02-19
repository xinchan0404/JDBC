package com.xinchan.jdbc.transaction;

import com.xinchan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class TransactionTest {
    @Test
    public void withoutTransaction() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String update1 = "update account set balance = balance - 100 where id = 1";
        String update2 = "update account set balance = balance + 100 where id = 2";
        int rows = 0;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(update1);
            rows += preparedStatement.executeUpdate();

            int a = 1 / 0;
            preparedStatement = connection.prepareStatement(update2);
            rows += preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, connection, preparedStatement);
        }
        if (rows > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void withTransaction() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String update1 = "update account set balance = balance - 100 where id = 1";
        String update2 = "update account set balance = balance + 100 where id = 2";
        int rows = 0;
        try {
            connection = JDBCUtils.getConnection();  // 默认情况下，connection 是自动提交事务的
            connection.setAutoCommit(false);  // 将 connection 设置为不自动提交，相当于开启事务
            preparedStatement = connection.prepareStatement(update1);
            rows += preparedStatement.executeUpdate();

//            int a = 1 / 0;
            preparedStatement = connection.prepareStatement(update2);
            rows += preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // SQL 语句执行失败或出现异常，回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, connection, preparedStatement);
        }
        if (rows > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
