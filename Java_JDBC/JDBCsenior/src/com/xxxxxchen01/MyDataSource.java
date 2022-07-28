package com.xxxxxchen01;

import com.xxxxxchen.utils.JDBCUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author KevinWilliams
 * 自定义数据库连接池
 */
public class MyDataSource implements DataSource {
    /**1.定义一个容器，用于保存多个Connection对象*/
    private static List<Connection> pool = Collections.synchronizedList(new ArrayList<>());

    /*2.定义静态代码块，通过JDBC工具类获取10个连接到容器*/
    static {
        for (int i = 1; i <= 10; i++) {
            Connection con = JDBCUtils.getConnection();
            pool.add(con);
        }
    }

    /**3.重写getConnection方法，获取连接并返回*/
    @Override
    public Connection getConnection() throws SQLException {
        if(pool.size() > 0){
            Connection con = pool.remove(0);
            return con;
        }else {
            throw new RuntimeException("连接数量用完");
        }
    }

    /**4.定义getSize()方法，用于获取容器大小并返回*/
    public int getSize(){
        return pool.size();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
