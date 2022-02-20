package com.xinchan.dao.daos;

import com.xinchan.jdbc.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 完成 CRUD 等基本操作
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class BasicDAO<T> {
    private QueryRunner queryRunner;

    public BasicDAO() {
        this.queryRunner = new QueryRunner();
    }

    /**
     * 更新数据库表
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        Connection connection = null;
        int rows;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            rows = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, connection, null);
        }

        return rows;
    }

    /**
     * 查询多行数据
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public List<T> selectMulti(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, connection, null);
        }
    }

    /**
     * 查询单行数据
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public T selectSingle(String sql, Class<T> clazz, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, connection, null);
        }
    }

    /**
     * 查询单列数据
     * @param sql
     * @param params
     * @return
     */
    public Object selectColumn(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, connection, null);
        }
    }
}
