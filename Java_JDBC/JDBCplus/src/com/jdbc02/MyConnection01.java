package com.jdbc02;

import com.mysql.jdbc.JDBC4Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author KevinWilliams
 * 自定义连接对象
 */
public class MyConnection01 extends JDBC4Connection {
    private Connection con;
    private List<Connection> pool;

    public MyConnection01(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url, Connection con, List<Connection> pool) throws SQLException {
        super(hostToConnectTo, portToConnectTo, info, databaseToConnectTo, url);
        this.con = con;
        this.pool = pool;
    }

    @Override
    public void close() throws SQLException {
        pool.add(con);
    }
}
