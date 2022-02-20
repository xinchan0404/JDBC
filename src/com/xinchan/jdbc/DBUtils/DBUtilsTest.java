package com.xinchan.jdbc.DBUtils;

import com.xinchan.jdbc.utils.Actor;
import com.xinchan.jdbc.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * 对 DBUtils 测试：多行数据、单行数据、单个（单行单列）数据
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class DBUtilsTest {
    @Test
    public void DBUtilsTestSelectMulti() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String select = "select * from actor";
        List<Actor> list = queryRunner.query(connection, select, new BeanListHandler<>(Actor.class));

        for (Actor actor : list) {
            System.out.println(actor);
        }

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }

    @Test
    public void DBUtilsTestSelectSingle() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String select = "select * from actor where id = ?";
        Actor actor = queryRunner.query(connection, select, new BeanHandler<>(Actor.class), 1);

        System.out.println(actor);

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }

    @Test
    public void DBUtilsTestSelectObject() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String select = "select name from actor where id = ?";
        // 因为返回的是一个对象, 使用的 handler 就是 ScalarHandler
        Object obj = queryRunner.query(connection, select, new ScalarHandler(), 1);

        System.out.println(obj);

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }

    @Test
    public void DBUtilsTestInsert() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String insert = "insert into actor values(null, 'xinchan', 'male', '1996-04-04', '6000')";
        // 因为返回的是一个对象, 使用的 handler 就是 ScalarHandler
        int rows = queryRunner.update(connection, insert);

        System.out.println(rows > 0 ? "执行成功" : "执行没有影响表");

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }

    @Test
    public void DBUtilsTestUpdate() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String update = "update actor set name = ? where id = ?";
        // 因为返回的是一个对象, 使用的 handler 就是 ScalarHandler
        int rows = queryRunner.update(connection, update, "zegxn", 2);

        System.out.println(rows > 0 ? "执行成功" : "执行没有影响表");

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }

    @Test
    public void DBUtilsTestDelete() throws SQLException {
        // 使用 apache-DBUtils 工具类 + Druid 完成对表的 CRUD 操作
        // 1. 获取连接（Druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2. 创建 QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        String delete = "delete from actor where id = ?";
        // 因为返回的是一个对象, 使用的 handler 就是 ScalarHandler
        int rows = queryRunner.update(connection, delete, 4);

        System.out.println(rows > 0 ? "执行成功" : "执行没有影响表");

        // 3. 释放资源（Druid），query 方法会关闭 resultSet 和 PreparedStatement
        JDBCUtilsByDruid.close(null, connection, null);
    }
}
