package com.xinchan.jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class JDBCUtilsByDruidTest {
    @Test
    public void JDBCUtilsByDruidTest() {
        // 1. 获取连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection.getClass());
        // 2. 执行 SQL 语句
        String select = "select * from admin";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                System.out.println(name + "\t" + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 3. 断开连接
            JDBCUtilsByDruid.close(resultSet, connection, preparedStatement);
        }
    }

    @Test
    public void table2Object() {
        // 1. 获取连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection.getClass());
        // 2. 执行 SQL 语句
        String select = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Actor> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Date birthday = resultSet.getDate("birthday");
                String phone = resultSet.getString("phone");
                // 把得到的 resultSet 的记录，封装到Actor 对象，放入到list 集合
                list.add(new Actor(id, name, sex, birthday, phone));
            }

            for (Actor actor : list) {
                System.out.println(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 3. 断开连接
            JDBCUtilsByDruid.close(resultSet, connection, preparedStatement);
        }
        // 因为 connection 和 List<Actor> 没有关联，因此该集合的数据可以复用
//        return list;
    }
}
