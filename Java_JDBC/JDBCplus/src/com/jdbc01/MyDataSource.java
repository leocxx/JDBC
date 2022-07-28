package com.jdbc01;



import com.jdbc.utils.JDBCUtils;
import com.jdbc02.MyConnection02;
import com.jdbc02.MyConnection03;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

    /**4.定义getSize()方法，用于获取容器大小并返回*/
    public int getSize(){
        return pool.size();
    }

    /**3.重写getConnection方法，获取连接并返回*/
    @Override
    public Connection getConnection() throws SQLException {
        if(pool.size() > 0){
            Connection con = pool.remove(0);
           //通过动态代理的方式
            Connection proxyCon = (Connection) Proxy.newProxyInstance(con.getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(method.getName().equals("close")){
                        pool.add(con);
                        return null;
                    }else {
                        return method.invoke(con,args);
                    }
                }
            });
            return proxyCon;
        }else {
            throw new RuntimeException("连接数量用完");
        }
    }
    /**3.重写getConnection方法，获取连接并返回
    //@Override
    public Connection getConnection() throws SQLException {
        if(pool.size() > 0){
            Connection con = pool.remove(0);
            //通过自定义的连接对象，对原有的进行包装
            //MyConnection02 mycon = new MyConnection02(con,pool);
            MyConnection03 mycon = new MyConnection03(con,pool);
            return mycon;
        }else {
            throw new RuntimeException("连接数量用完");
        }
    }*/

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
