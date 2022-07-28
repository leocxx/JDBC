package com.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author KevinWilliams
 * ；连接池的工具类
 */
public class DataSourceUtils {
    /**私有化构造方法*/
    private DataSourceUtils(){}

    /**声明数据源变量*/
    private static DataSource dataSource;

    /*提供静态代码块，完成配置文件的加载和获取数据库连接池对象*/
    static {
        try {
            //配置文件的加载
            InputStream is = DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties prop = new Properties();
            prop.load(is);

            //获取数据库连接池对象
             dataSource = DruidDataSourceFactory.createDataSource(prop);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**提供一个获取数据库连接的方法*/
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**提供一个获取数据建库连接池对象的方法*/
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**释放资源的方法*/
    public static void close(Connection con, Statement stat, ResultSet sr){
        if(con!= null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!= null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(sr!= null){
            try {
                sr.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con, Statement stat){
        if(con!= null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!= null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
