package utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class JDBCUtilsTest {
    @Test
    public void JDBCUtilsTestMethod() throws SQLException {
        // 1. 注册驱动，创建连接
        Connection connection = JDBCUtils.getConnection();

        // 2. SQL 语句
        String insert = "insert into admin values(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, "jack");
        preparedStatement.setString(2, "jack");
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        // 3. 释放资源
        JDBCUtils.close(null, connection, preparedStatement);
    }
}
