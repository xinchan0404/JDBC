package com.xinchan.jdbc.datasource;

import com.xinchan.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-19
 */
public class ConnectionQuestion {
    @Test
    public void question() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.close(null, connection, null);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
    }
}
