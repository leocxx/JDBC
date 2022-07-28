package com.jdbc02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author KevinWilliams
 */
public class MyConnection03 extends MyAdapter{
    private Connection con;
    private List<Connection> pool;

    public MyConnection03 (Connection con, List<Connection> pool){
        super(con);
        this.con = con;
        this.pool = pool;
    }

    @Override
    public void close() throws SQLException {
        pool.add(con);
    }
}
