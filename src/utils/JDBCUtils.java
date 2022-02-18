package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            /*
             * 在实际开发中，我们可以这样做
             * 1. 将编译型异常转换为运行时异常
             * 2. 调用者，可以选择捕获该异常，也可以选择默认处理该异常，比较方便
             */
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            // 1. 注册驱动
            Class.forName(driver);
            // 2. 创建连接
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet, Connection connection, Statement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
