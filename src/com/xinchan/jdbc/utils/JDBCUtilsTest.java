package com.xinchan.jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class JDBCUtilsTest {
    @Test
    public void JDBCUtilsTestInsert(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insert = "insert into admin values(?, ?)";
        int i = 0;
        try {
            // 1. 注册驱动
            connection = JDBCUtils.getConnection();
            // 2. 创建连接，获取 preparedStatement
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, "jack");
            preparedStatement.setString(2, "jack");
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 3. 释放资源
            JDBCUtils.close(null, connection, preparedStatement);
        }
        if (i > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void JDBCUtilsTestUpdate(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String update = "update admin set password = ? where name = ?";
        int i = 0;
        try {
            // 1. 注册驱动并获取连接
            connection = JDBCUtils.getConnection();
            // 2. 获取 preparedStatement
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, "123");
            preparedStatement.setString(2, "tom");
            // 3. 执行 SQL 语句
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源
            JDBCUtils.close(null, connection, preparedStatement);
        }
        if (i > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void JDBCUtilsTestDelete(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String delete = "delete from admin where name = ?";
        int i = 0;
        try {
            // 1. 注册驱动并获取连接
            connection = JDBCUtils.getConnection();
            // 2. 获取 preparedStatement
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, "jack");
            // 3. 执行 SQL 语句
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源
            JDBCUtils.close(null, connection, preparedStatement);
        }
        if (i > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void JDBCUtilsTestSelect(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String select = "select name, password from admin";
        int i = 0;
        try {
            // 1. 注册驱动并获取连接
            connection = JDBCUtils.getConnection();
            // 2. 获取 preparedStatement
            preparedStatement = connection.prepareStatement(select);
            // 3. 执行 SQL 语句
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                System.out.println(name + "\t" + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源
            JDBCUtils.close(resultSet, connection, preparedStatement);
        }
    }
}
