package com.xxxxxchen02;

import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author KevinWilliams
 * 自定义连接对象
 */
public class MyConnection01 extends ConnectionImpl {//1.继承ConnectionImpl
    private Connection con;
    private List<Connection> pool;

    public MyConnection01(HostInfo hostInfo, Connection con, List<Connection> pool) throws SQLException {
        super(hostInfo);
        this.con = con;
        this.pool = pool;
    }

    @Override
    public void close() throws SQLException {
        pool.add(con);
    }
}
